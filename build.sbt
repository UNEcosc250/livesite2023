enablePlugins(ScalaJSPlugin)

name := "Will Scala"
scalaVersion := "3.3.5"

// This is an application with a main method
scalaJSUseMainModuleInitializer := true

// Because we then use webpack to build the final output
// Note, we use the CommonJSModule output, as otherwise Scala.js will skip using the Closure compiler to
// shrink the full output.
scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.CommonJSModule) }

updateOptions := updateOptions.value.withLatestSnapshots(false)

libraryDependencies ++= Seq(
  "com.wbillingsley" %%% "doctacular" % "0.3.0",
)

val deployScript = taskKey[Unit]("Copies the fullOptJS script to deployscripts/")

val deployFast = taskKey[Unit]("Copies the fastLinkJS script to deployscripts/")
val deployFull = taskKey[Unit]("Copies the fullLinkJS script to deployscripts/")

// Used by GitHub Actions to get the script out from the .gitignored target directory
deployFast := {
  val opt = (Compile / fastOptJS).value
  IO.copyFile(opt.data, new java.io.File("target/compiled.js"))
}

deployFull := {
  val opt = (Compile / fullOptJS).value
  IO.copyFile(opt.data, new java.io.File("target/compiled.js"))
}