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
- extract swt.jar and src.zip
- rename src.zip to src-sources.jar 
- call for binary and sources installation (see this [answer](https://stackoverflow.com/q/9386589/2313177)
```
mvn deploy:deploy-file ^
     -DgroupId=org.eclipse.swt ^
     -DartifactId=swt-win32-win32-x86_64 ^
     -Dversion=4.14 ^
     -Dfile=swt.jar ^
     -Dsources=src-sources.jar ^
     -Durl=file://C:\Home\Projects\book-lab\lib
```