#!/usr/bin/env bash

PMD_VERSION="6.22.0"
PMD_PATH="pmd-bin-${PMD_VERSION}"

if [ ! -e ${PMD_PATH} ]; then
    wget https://github.com/pmd/pmd/releases/download/pmd_releases%2F${PMD_VERSION}/pmd-bin-${PMD_VERSION}.zip
    unzip pmd-bin-${PMD_VERSION}.zip
fi

echo "PMD location: $(pwd)/${PMD_PATH}"
