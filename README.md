# ModuleMaker

to use both AppLogger and Extensions use

Add it in your root build.gradle at the end of repositories:

allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}



dependencies {
	        implementation 'com.github.anish59:ModuleMaker:Tag'
	}

To only use AppLogger:
implementation 'com.github.anish59.ModuleMaker:apputlility:1.0.0'

To only use 
