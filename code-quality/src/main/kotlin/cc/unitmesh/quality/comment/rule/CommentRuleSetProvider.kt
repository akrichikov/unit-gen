package cc.unitmesh.quality.comment.rule

import org.archguard.rule.core.RuleSet
import org.archguard.rule.core.RuleSetProvider
import org.archguard.rule.core.RuleType

class CommentRuleSetProvider: RuleSetProvider {
    override fun get(): RuleSet {
        return RuleSet(
            RuleType.CODE_SMELL,
            "normal",
            MissingParameterDescRule()
        )
    }
}
