package net.codesanctum.processors

import com.winterbe.expekt.should
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.it

class ExampleTest : Spek({
    it("should be success") {
        1.should.be.equal(1)
    }
})