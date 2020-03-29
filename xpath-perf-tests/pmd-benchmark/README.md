# PMD Benchmarks

That's a manual process for now, but could be possibly integration into [PMD Regression Tester](https://github.com/pmd/pmd-regression-tester).

## Java

Results from 2020-03-29 - see [summary](results-2020-03-29/summary.md)

### Preparation
```
git clone --branch v5.0.6.RELEASE https://github.com/spring-projects/spring-framework
wget https://github.com/pmd/pmd/releases/download/pmd_releases%2F6.22.0/pmd-bin-6.22.0.zip
unzip pmd-bin-6.22.0.zip
wget https://github.com/pmd/pmd/blob/master/pmd-dist/src/test/resources/rulesets/all-java.xml
```

### Run
```
export LANG=en.utf-8 # important for decimal separators...
time pmd-bin-6.22.0/bin/run.sh pmd -benchmark -no-cache -d spring-framework -f xml -R all-java.xml -r spring-framework-report.xml 2>&1 | tee spring-framework-err.txt
```

### Post processing
```
sort spring-framework-err.txt |grep -v "WARNING"|grep -v "apex,"|grep -v "SEVERE"|grep -v "^... " | grep -v "^$" | grep -v "^ "|grep -v "^---" |grep -v "Label " > spring-framework-err_sorted.txt
```

### Scripted

```
./run-benchmark.sh spring-framework pmd-bin-6.22.0
```


## Apex

### Preparation
```
git clone --branch master https://github.com/apex-enterprise-patterns/fflib-apex-common
wget https://github.com/pmd/pmd/releases/download/pmd_releases%2F6.22.0/pmd-bin-6.22.0.zip
unzip pmd-bin-6.22.0.zip
wget https://raw.githubusercontent.com/pmd/pmd/master/pmd-dist/src/test/resources/rulesets/all-apex.xml
```

### Run
```
export LANG=en.utf-8 # important for decimal separators...
time pmd-bin-6.22.0/bin/run.sh pmd -benchmark -no-cache -d fflib-apex-common -f xml -R all-apex.xml -r fflib-apex-common-report.xml 2>&1 | tee fflib-apex-common-err.txt
```

### Post processing
```
sort fflib-apex-common-err.txt |grep -v "WARNING"|grep -v "apex,"|grep -v "SEVERE"|grep -v "^... " | grep -v "^$" | grep -v "^ "|grep -v "^---" |grep -v "Label " > fflib-apex-common-err_sorted.txt
```
