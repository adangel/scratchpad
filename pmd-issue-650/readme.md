See <https://github.com/pmd/pmd/issues/650>

Run `mvn clean verify -Dpmd.skipPmdError=false` to see the problem.

Use `mvn -Dmaven.repo.local=$(pwd)/m2repo/ -Dpmd.skipPmdError=false clean verify` for a fresh local maven repo.

