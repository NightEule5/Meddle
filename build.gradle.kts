@file:Suppress("UnstableApiUsage")

import com.squareup.wire.gradle.WireExtension

buildscript {
	repositories {
		mavenCentral()
	}
	
	dependencies {
		classpath(group = "com.squareup.wire", name = "wire-gradle-plugin", version = "3.7.0")
	}
}

plugins {
	kotlin("multiplatform")        version "1.5.0-RC"
	kotlin("plugin.serialization") version "1.5.0-RC"
}

apply(plugin = "com.squareup.wire") // Whyyyyy? :(

extensions.configure<WireExtension>
{
	sourcePath()
	{
		srcDir("src/commonMain/proto")
	}
	
	kotlin()
	{
		rpcRole = "server"
	}
}

group = "strixpyrr.meddle"
version = "0.0.1"

repositories {
	mavenCentral()
}

dependencies {
	commonMainImplementation(kotlin("stdlib-common"))
}

kotlin {
	jvm("server")
	{
		val main by compilations.getting()
		{
			kotlinOptions.run()
			{
				jvmTarget       = "15"
				languageVersion = "1.5"
			}
			
			dependencies()
			{
				implementation(libraries.clikt)
				implementation(libraries.kotlinx.serialization.json)
				implementation(libraries.okio)
			}
		}
		
		// withJava()?
	}
	
	js("client", IR)
	{
		browser()
		{
			// Todo
		}
	}
	
	sourceSets {
		val commonMain by getting {
			kotlin.srcDir("src/commonMain/proto")
		}
		
		val serverMain by getting {
			dependencies {
				implementation(kotlin("stdlib-jdk8"))
			}
		}
		
		val clientMain by getting {
			dependencies {
				implementation(kotlin("stdlib-js"))
			}
		}
	}
}