package net.codesanctum.processors

import com.google.auto.service.AutoService
import com.squareup.javapoet.JavaFile
import com.squareup.javapoet.TypeSpec
import net.codesanctum.annotations.Builder
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.Modifier
import javax.lang.model.element.TypeElement
import javax.lang.model.util.Elements

@AutoService(Processor::class)
class BuilderProcessor : AbstractProcessor() {
    private lateinit var elementUtils: Elements
    private lateinit var filer: Filer

    override fun init(processingEnvironment: ProcessingEnvironment) {
        super.init(processingEnvironment)
        elementUtils = processingEnvironment.elementUtils
        filer = processingEnvironment.filer
    }

    override fun process(set: MutableSet<out TypeElement>, roundEnvironment: RoundEnvironment): Boolean {
        val elements = roundEnvironment.getElementsAnnotatedWith(Builder::class.java)
        elements?.forEach {
            val element = it
            val packageName = elementUtils.getPackageOf(element)?.qualifiedName.toString()
            val className = "Generated${element.simpleName}"
            val outputType = TypeSpec.classBuilder(className)
                .addModifiers(Modifier.PUBLIC)
            val javaFile = JavaFile.builder(packageName, outputType.build())
                .addFileComment("This file is auto-generated and should not be edited.")
                .build()
            javaFile.writeTo(filer)
        }
        return true
    }

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        return mutableSetOf(Builder::class.java.canonicalName)
    }

    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.latestSupported()
    }
}