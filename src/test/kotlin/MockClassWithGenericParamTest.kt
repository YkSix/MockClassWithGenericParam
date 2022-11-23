import org.junit.Rule
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

/* open */ class ConcreteStringClass : AbstractGenericTypeClass<String>()

abstract class AbstractGenericTypeClass<T : CharSequence> {
    /**
     * Removing either one of 'open' and 'suspend' can fix 'MockitoException'
     */
    open suspend fun bar() = Unit
}

/**
 * (Kotlin >= 1.7.20) Can't mock class inheriting from a generic class with a "open suspend" function
 * https://github.com/mockito/mockito/issues/2795
 */
class MockClassWithGenericParamTest {
    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var concreteStringClass: ConcreteStringClass

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun test() {
        println("concreteStringClass = $concreteStringClass")
    }
}
