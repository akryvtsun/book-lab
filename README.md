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
> mvn install:install-file -Dfile=swt.jar -DgroupId=org.eclipse.swt -DartifactId=swt-win32-win32-x86_64 -Dversion=4.14 -Dpackaging=jar -DlocalRepositoryPath=C:\Home\Projects\book-lab\lib
