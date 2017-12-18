# Customized PMD Distribution with only Apex and Visualforce

Based on the original [pmd-dist module](https://github.com/pmd/pmd/blob/master/pmd-dist/pom.xml).

## Building

    mvn clean package

The resulting binary is in `target/pmd-bin-apex-vf-6.0.0.zip`


## Updating PMD version

Theoretically just change the parent version reference in the `pom.xml`.
