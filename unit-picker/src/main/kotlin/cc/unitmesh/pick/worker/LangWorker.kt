package cc.unitmesh.pick.worker

import cc.unitmesh.pick.builder.InstructionFileJob
import cc.unitmesh.pick.prompt.Instruction

abstract class LangWorker {
    abstract fun addJob(job: InstructionFileJob)
    abstract suspend fun start() : Collection<Instruction>
}