package cc.unitmesh.pick.builder

import cc.unitmesh.core.completion.CompletionBuilder
import cc.unitmesh.core.completion.CompletionBuilderType
import cc.unitmesh.core.unittest.TestCodeBuilderType
import cc.unitmesh.core.comments.CommentsBuilder
import cc.unitmesh.pick.builder.bizcode.AfterBlockCodeCompletionBuilder
import cc.unitmesh.pick.builder.bizcode.InBlockCodeCompletionBuilder
import cc.unitmesh.pick.builder.bizcode.InlineCodeCompletionBuilder
import cc.unitmesh.pick.builder.unittest.java.ClassTestCodeBuilder
import cc.unitmesh.pick.builder.unittest.java.JavaMethodTestCodeBuilder
import cc.unitmesh.pick.worker.job.JobContext
import kotlinx.serialization.SerializationException

fun completionBuilders(types: List<CompletionBuilderType>, context: JobContext) : List<CompletionBuilder> {
    return types.map { completionBuilder(it, context) }
}

fun completionBuilder(completionBuilderType: CompletionBuilderType, context: JobContext): CompletionBuilder {
    return mapOf(
        CompletionBuilderType.INLINE_COMPLETION to InlineCodeCompletionBuilder(context),
        CompletionBuilderType.IN_BLOCK_COMPLETION to InBlockCodeCompletionBuilder(context),
        CompletionBuilderType.AFTER_BLOCK_COMPLETION to AfterBlockCodeCompletionBuilder(context),
        CompletionBuilderType.TEST_CODE_GEN to TestCodeCompletionBuilder(context),
        CompletionBuilderType.DOCUMENTATION to DocumentationCompletionBuilder(context),
    )[completionBuilderType] ?: throw SerializationException("Unknown message type: $completionBuilderType")
}

fun testBuilders(types: List<TestCodeBuilderType>, context: JobContext) : List<CommentsBuilder> {
    return types.map { testBuilder(it, context) }
}

fun testBuilder(type: TestCodeBuilderType, context: JobContext): CommentsBuilder {
    return mapOf(
        TestCodeBuilderType.METHOD_UNIT to JavaMethodTestCodeBuilder(context),
        TestCodeBuilderType.CLASS_UNIT to ClassTestCodeBuilder(context),
    )[type] ?: throw SerializationException("Unknown message type: $type")
}
