# openmrs-module-orderexpansion

## Overview

The Order Expansion module enhances the order management capabilities of KenyaEMR, an implementation of OpenMRS. It introduces support for additional order types, including:

1. Procedure Orders
2. Radiology Orders (e.g., imaging, scans)
3. Medical Supply Orders (e.g., consumables, laboratory reagents)

This expansion allows for more comprehensive and specialized order management within the KenyaEMR system.

## Features

- Seamless integration with existing KenyaEMR order management
- Support for procedure, radiology, and medical supply order types

## Prerequisites

- Java 1.8 or higher
- Maven 3.x or higher
- OpenMRS Platform 2.x (specific version compatibility to be verified)

## Building from Source

To build the module from source:

1. Clone the repository:
   ```
   git clone https://github.com/your-repo/openmrs-module-orderexpansion.git
   cd openmrs-module-orderexpansion
   mvn clean install
   ```

2. Compile and package the module:
   ```
   mvn clean package
   ```

3. The compiled OMOD file will be available in the `omod/target` folder.

4. Configure the module settings as needed. With relevant metadata. 
   See [here](https://github.com/palladiumkenya/openmrs-config-kenyaemr/blob/main/configuration/ordertypes/ordertypes.csv) for extended order types.

## Installation

### Option 1: Web Upload (Recommended)

1. Log in to your OpenMRS implementation as an administrator.
2. Navigate to `Administration` > `Manage Modules`.
3. Click on `Add or Upgrade Module`.
4. Choose the OMOD file and upload it.

### Option 2: Manual Installation

1. Copy the OMOD file to the OpenMRS application data directory:
   ```
   ~/.OpenMRS/modules/
   ```
   Note: The actual path may vary depending on your OpenMRS configuration.

2. Restart the OpenMRS application.
