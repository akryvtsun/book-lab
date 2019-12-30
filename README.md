# Book Lab

[SWT Demo Snippets](https://www.eclipse.org/swt/snippets/)

#### How to download SWT release 
- go to [The Eclipse Project Downloads](https://download.eclipse.org/eclipse/downloads/)
- select in 'Latest Downloads' the latest release under 'Build Name' column. E.g. `4.14`
- go to 'SWT Binary and Source' section
- download zip archive for needed platform

#### Hot to install SWT into local project Maven repo
- download the latest SWT release for needed platform
- put zip file into the project root folder
- call 
> mvnw org.apache.maven.plugins:maven-install-plugin:3.0.0-M1:install-file -Dfile=swt-4.14-win32-win32-x86_64.zip -DgroupId=org.eclipse.swt -DartifactId=swt-win32-win32-x86_64.zip -Dversion=4.14 -Dpackaging=jar -DlocalRepositoryPath=lib