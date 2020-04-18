## Methodology


I made one data point for each of `(jdk8,jdk11)x(1,4,16 threads)x(commit...)`, where "commit" is one of 
* tag 6.22.0 (the baseline)
* [de875c9](https://github.com/pmd/pmd/pull/2377/commits/de875c955a5c030e035e5a44b7919d369666a099) (before the optimisations I added, what I named before-me in the results) 
* [c409809](https://github.com/pmd/pmd/pull/2377/commits/c4098096724ebdcdcfa6d0b4d0e2d5ad1d8b79bf) (the latest version of this branch)

Further notes
* I didn't bother with jdk8x16 threads. 16 is theoretically the number of virtual cores supported by my processor, but on this machine, for PMD, 4 threads is the point of diminishing returns. I did some tests with 16 threads just to be sure about the contention on the name pool.
* I didn't do any per-rule analysis as these optimisations should touch every rule.

The usual shtick about "these measurements are just numbers" obviously applies, ie these are probably only reproducible on my machine and would depend on what I was doing in the meantime, and there's only one data point per configuration. But the general trend is pretty clear.


## Results

Results are summarized in summary.ods, execution logs are in the now-* directories
