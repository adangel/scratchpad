#!/usr/bin/env bash


function main()
{
    local name=$1
    local pathToPmd=$2
    if [ -z $name -o -z $pathToPmd ]; then
        echo "$0 <name> <pathToPmd>"
        echo
        echo "e.g. $0 baseline pmd-bin-6.22.0"
        exit 1
    fi
    
    log "Simple PMD Benchmarker"
    log "----------------------"
    prepare
    run $name $pathToPmd
    convert_numbers $name
}

function log()
{
    local message=$1
    echo "$(date) $message"
}

function prepare()
{
    log "Preparing..."
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
    local name=$1
    local pathToPmd=$2
    local report="${name}-report.xml"
    local log="${name}-log.txt"
    local ruleset="all-java.xml"
    local code="spring-framework"

    if [ ! -e $pathToPmd/bin/run.sh ]; then
        log "File $pathToPmd/bin/run.sh not found!"
        exit 1
    fi
    
    if [ -e $report -o -e $log ]; then
        log "File $report or $log already exists - not running again!"
        exit 1
    fi
    
    log "Running PMD..."
    export LANG=en.utf-8 # important for decimal separators...
    time $pathToPmd/bin/run.sh pmd -benchmark -no-cache -d $code -f xml -R $ruleset -r $report 2>&1 | tee $log
    log "Finished running PMD".
}

function convert_numbers()
{
    local name=$1
    local log="${name}-log.txt"
    local csv="${name}-data.csv"

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
