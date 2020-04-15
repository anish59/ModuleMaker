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
