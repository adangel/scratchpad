PMD_HOME=/home/andreas/PMD/pmd/pmd-dist/target/pmd-bin-6.20.0-SNAPSHOT

${PMD_HOME}/bin/run.sh pmd -R rulesets/java/quickstart.xml -d src -f text -l java -v 6 -D
