@file:Suppress("UnstableApiUsage")

rootProject.name = "Meddle"

enableFeaturePreview("VERSION_CATALOGS")

dependencyResolutionManagement()
{
	versionCatalogs()
	{
		create("libraries")
		{
			alias("clikt"                     ).to("com.github.ajalt.clikt", "clikt"                    ).version("3.1.0")
			alias("kotlinx-serialization-json").to("org.jetbrains.kotlinx", "kotlinx-serialization-json").version("1.1.0")
			alias("okio"                      ).to("com.squareup.okio", "okio"                          ).version("3.0.+")
		}
	}
}
