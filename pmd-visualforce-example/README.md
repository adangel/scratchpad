# Example for using PMD with Visualforce

## Run PMD

```
prepare.sh
pmd-bin-6.22.0/bin/run.sh pmd -d src -R vf-ruleset.xml -f xml -r pmd-report.xml
```

The result is in `pmd-report.xml`:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<pmd xmlns="http://pmd.sourceforge.net/report/2.0.0"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://pmd.sourceforge.net/report/2.0.0 http://pmd.sourceforge.net/report_2_0_0.xsd"
    version="6.22.0" timestamp="2020-04-03T10:00:03.808">
<file name="/home/andreas/PMD/source/scratchpad/pmd-visualforce-example/src/pages/MyPage.page">
<violation beginline="2" endline="2" begincolumn="57" endcolumn="73" rule="VfCsrf" ruleset="Security" externalInfoUrl="https://pmd.github.io/pmd-6.22.0/pmd_rules_vf_security.html#vfcsrf" priority="3">
Avoid calling VF action upon page load
</violation>
</file>
</pmd>
```

## Documentation

*   https://pmd.github.io/latest/pmd_userdocs_installation.html
*   https://pmd.github.io/latest/pmd_userdocs_making_rulesets.html
*   https://developer.salesforce.com/docs/atlas.en-us.pages.meta/pages/pages_compref_page.htm
