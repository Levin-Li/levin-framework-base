package com.levin.oak.base;

import static com.levin.oak.base.ModuleOption.*;
import com.levin.commons.service.support.AbstractAppDataInitializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 *  应用数据初始化
 *  @author Auto gen by simple-dao-codegen 2022-3-29 23:18:11
 */

@Component(PLUGIN_PREFIX + "FreeMarker template error (DEBUG mode; use RETHROW in production!):
The following has evaluated to null or missing:
==> className  [in template "simple-dao/codegen/template/testcase/AppDataInitializer.java" at line 14, column 31]

----
Tip: If the failing expression is known to legally refer to something that's sometimes null or missing, either specify a default value like myOptionalVar!myDefault, or use <#if myOptionalVar??>when-present<#else>when-missing</#if>. (These only cover the last step of the expression; to cover the whole expression, use parenthesis: (myOptionalVar.foo)!myDefault, (myOptionalVar.foo)??
----

----
FTL stack trace ("~" means nesting-related):
	- Failed at: ${className}  [in template "simple-dao/codegen/template/testcase/AppDataInitializer.java" at line 14, column 29]
----

Java stack trace (for programmers):
----
freemarker.core.InvalidReferenceException: [... Exception message was already printed; see it above ...]
	at freemarker.core.InvalidReferenceException.getInstance(InvalidReferenceException.java:134)
	at freemarker.core.EvalUtil.coerceModelToTextualCommon(EvalUtil.java:481)
	at freemarker.core.EvalUtil.coerceModelToStringOrMarkup(EvalUtil.java:401)
	at freemarker.core.EvalUtil.coerceModelToStringOrMarkup(EvalUtil.java:370)
	at freemarker.core.DollarVariable.calculateInterpolatedStringOrMarkup(DollarVariable.java:100)
	at freemarker.core.DollarVariable.accept(DollarVariable.java:63)
	at freemarker.core.Environment.visit(Environment.java:334)
	at freemarker.core.Environment.visit(Environment.java:340)
	at freemarker.core.Environment.process(Environment.java:313)
	at freemarker.template.Template.process(Template.java:383)
	at com.levin.commons.dao.codegen.ServiceModelCodeGenerator.genFileByTemplate(ServiceModelCodeGenerator.java:904)
	at com.levin.commons.dao.codegen.ServiceModelCodeGenerator.tryGenTestcase(ServiceModelCodeGenerator.java:226)
	at com.levin.commons.dao.codegen.plugins.CodeGeneratorMojo.executeMojo(CodeGeneratorMojo.java:204)
	at com.levin.commons.plugins.BaseMojo.execute(BaseMojo.java:472)
	at org.apache.maven.plugin.DefaultBuildPluginManager.executeMojo(DefaultBuildPluginManager.java:137)
	at org.apache.maven.lifecycle.internal.MojoExecutor.execute(MojoExecutor.java:210)
	at org.apache.maven.lifecycle.internal.MojoExecutor.execute(MojoExecutor.java:156)
	at org.apache.maven.lifecycle.internal.MojoExecutor.execute(MojoExecutor.java:148)
	at org.apache.maven.lifecycle.internal.LifecycleModuleBuilder.buildProject(LifecycleModuleBuilder.java:117)
	at org.apache.maven.lifecycle.internal.LifecycleModuleBuilder.buildProject(LifecycleModuleBuilder.java:81)
	at org.apache.maven.lifecycle.internal.builder.singlethreaded.SingleThreadedBuilder.build(SingleThreadedBuilder.java:56)
	at org.apache.maven.lifecycle.internal.LifecycleStarter.execute(LifecycleStarter.java:128)
	at org.apache.maven.DefaultMaven.doExecute(DefaultMaven.java:305)
	at org.apache.maven.DefaultMaven.doExecute(DefaultMaven.java:192)
	at org.apache.maven.DefaultMaven.execute(DefaultMaven.java:105)
	at org.apache.maven.cli.MavenCli.execute(MavenCli.java:957)
	at org.apache.maven.cli.MavenCli.doMain(MavenCli.java:289)
	at org.apache.maven.cli.MavenCli.main(MavenCli.java:193)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.codehaus.plexus.classworlds.launcher.Launcher.launchEnhanced(Launcher.java:282)
	at org.codehaus.plexus.classworlds.launcher.Launcher.launch(Launcher.java:225)
	at org.codehaus.plexus.classworlds.launcher.Launcher.mainWithExitCode(Launcher.java:406)
	at org.codehaus.plexus.classworlds.launcher.Launcher.main(Launcher.java:347)
	at org.codehaus.classworlds.Launcher.main(Launcher.java:47)
