PMD_PATH=/home/andreas/PMD/binaries/pmd-bin-6.20.0
#PMD_PATH=/home/andreas/PMD/binaries/pmd-bin-6.17.0

# note: without auxclasspath
${PMD_PATH}/bin/run.sh pmd -no-cache -f text -R category/java/bestpractices.xml/UnusedPrivateMethod -d src/main/java
