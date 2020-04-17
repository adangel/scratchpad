#!/usr/bin/env bash

function main()
{
    local resultDir=$1
    local name=$2
    local pathToPmd=$3
    if [ -z "$resultDir" -o -z "$name" -o -z "$pathToPmd" ]; then
        echo "$0 <resultDir> <name> <pathToPmd>"
        echo
        echo "e.g. $0 results-2020-04-16 baseline pmd-bin-6.22.0"
        exit 1
    fi
    
    log "Simple PMD Benchmarker"
    log "----------------------"
    prepare $resultDir
    run $resultDir $name $pathToPmd
    convert_numbers $resultDir $name
}

function log()
{
    local message=$1
    echo "$(date) $message"
}

function prepare()
{
    local resultDir=$1
    log "Preparing..."
    
    mkdir -p $resultDir
    log "  resultDir=$resultDir"
    
    if [ ! -e spring-framework ]; then
        git clone --branch v5.0.6.RELEASE https://github.com/spring-projects/spring-framework
    else
        log " spring framework already cloned"
    fi
    
    if [ ! -e all-java.xml ]; then
        wget https://github.com/pmd/pmd/blob/master/pmd-dist/src/test/resources/rulesets/all-java.xml
    else
        log " all-java.xml already downloaded"
    fi
    log "Preparation done!"
}

function run()
{
    local resultDir=$1
    local name=$2
    local pathToPmd=$3
    local report="${resultDir}/${name}-report.xml"
    local log="${resultDir}/${name}-log.txt"
    local ruleset="rulesets/internal/all-java.xml"
    local code="spring-framework"
    local numthreads=$PMD_THREADS

    if [ -z $numthreads ]; then
	log "Monothreaded run"
	numthreads=1
    else
	log "Running with $numthreads threads"
    fi

    if [ ! -e $pathToPmd/bin/run.sh ]; then
        log "File $pathToPmd/bin/run.sh not found!"
        exit 1
    fi
    
    if [ -e $report -o -e $log ]; then
        log "File $report or $log already exists - not running again!"
        exit 1
    fi
    
    log "Running PMD..."
    export LANG=C.UTF-8 # important for decimal separators...
    echo "Running PMD: pathToPmd=$pathToPmd" > $log
    echo "java version" >> $log
    java --version >> $log
    time $pathToPmd/bin/run.sh pmd -benchmark -no-cache -d $code -f xml -R $ruleset -r $report -t $numthreads 2>&1 | tee -a $log
    log "Finished running PMD".
}

function convert_numbers()
{
    local resultDir=$1
    local name=$2
    local log="${resultDir}/${name}-log.txt"
    local csv="${resultDir}/${name}-data.csv"

    if [ ! -e $log ]; then
        log "File $log is missing!"
        exit 1
    fi
    if [ -e $csv ]; then
        log "File $csv already exists!"
        exit 1
    fi

    local startLine="$(grep -n "<<< Rule" $log | head -n 1| cut -d: -f1)"
    local endLine="$(grep -n "<<< Summary" $log | cut -d: -f1)"
    local lines=$(($endLine - $startLine))
    local data="$(cat $log | head -n $endLine | tail -n -$(($endLine - $startLine)))"
    data="$(echo "$data" | egrep -v "^$|^---|^Total |^Label "|sort)"
    local ruleCount="$(echo "$data" | wc -l)"
    log "Rule count: $ruleCount"
    
    echo "PMD Benchmark: $name ($(date))" > $csv
    echo "Rule;Duration" >> $csv
    echo "$data" | while read line
    do
        local ruleName=$(echo $line | cut -d" " -f1)
        local duration=$(echo $line | cut -d" " -f2)
        echo "$ruleName;$duration" >> $csv
    done
    
    log "Created $csv"
}


main $*
