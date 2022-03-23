package com.springboot.model;

    import com.baomidou.mybatisplus.annotation.TableName;
    import java.io.Serializable;
    import java.time.LocalDateTime;
FreeMarker template error (DEBUG mode; use RETHROW in production!):
The following has evaluated to null or missing:
==> swagger2  [in template "templates/freemarker/entity.java.ftl" at line 6, column 6]

----
Tip: If the failing expression is known to legally refer to something that's sometimes null or missing, either specify a default value like myOptionalVar!myDefault, or use <#if myOptionalVar??>when-present<#else>when-missing</#if>. (These only cover the last step of the expression; to cover the whole expression, use parenthesis: (myOptionalVar.foo)!myDefault, (myOptionalVar.foo)??
----

----
FTL stack trace ("~" means nesting-related):
	- Failed at: #if swagger2  [in template "templates/freemarker/entity.java.ftl" at line 6, column 1]
----

Java stack trace (for programmers):
----
freemarker.core.InvalidReferenceException: [... Exception message was already printed; see it above ...]
	at freemarker.core.InvalidReferenceException.getInstance(InvalidReferenceException.java:134)
	at freemarker.core.UnexpectedTypeException.newDescriptionBuilder(UnexpectedTypeException.java:85)
	at freemarker.core.UnexpectedTypeException.<init>(UnexpectedTypeException.java:48)
	at freemarker.core.NonBooleanException.<init>(NonBooleanException.java:47)
	at freemarker.core.Expression.modelToBoolean(Expression.java:195)
	at freemarker.core.Expression.evalToBoolean(Expression.java:178)
	at freemarker.core.Expression.evalToBoolean(Expression.java:163)
	at freemarker.core.ConditionalBlock.accept(ConditionalBlock.java:48)
	at freemarker.core.Environment.visit(Environment.java:347)
	at freemarker.core.Environment.visit(Environment.java:353)
	at freemarker.core.Environment.process(Environment.java:326)
	at freemarker.template.Template.process(Template.java:383)
	at com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine.writer(FreemarkerTemplateEngine.java:52)
	at com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine.outputFile(AbstractTemplateEngine.java:184)
	at com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine.lambda$outputEntity$2(AbstractTemplateEngine.java:89)
	at java.util.Optional.ifPresent(Optional.java:159)
	at com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine.outputEntity(AbstractTemplateEngine.java:87)
	at com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine.lambda$batchOutput$9(AbstractTemplateEngine.java:235)
	at java.util.ArrayList.forEach(ArrayList.java:1257)
	at com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine.batchOutput(AbstractTemplateEngine.java:227)
	at com.baomidou.mybatisplus.generator.AutoGenerator.execute(AutoGenerator.java:179)
	at com.lbh.auto.generator.MysqlGenerator.autoGenerator(MysqlGenerator.java:70)
	at com.lbh.auto.generator.MysqlGenerator.main(MysqlGenerator.java:56)
