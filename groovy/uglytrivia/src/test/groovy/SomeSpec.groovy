import spock.lang.Specification

class SomeSpec extends Specification {

	def "should not work"(){
		when:
		def a = false

		then:
		a == true
	}

}
