package id.tanudjaja.libtaxindo;

import static org.junit.Assert.*;
import org.junit.*;
import org.junit.rules.ExpectedException;
import static org.hamcrest.CoreMatchers.*;

import static id.tanudjaja.libtaxindo.NonTaxableIncomeCounter.*;
import static id.tanudjaja.libtaxindo.constants.MaritalStatus.*;

public class TestNonTaxableIncomeCounter
{
	@Rule
	public ExpectedException mExceptionRule=ExpectedException.none();

	@Test
	public void testCalculateWithBadArgument()
	{
		mExceptionRule.expect(IllegalArgumentException.class);
		mExceptionRule.expectMessage(BAD_ENUM);

		NonTaxableIncomeCounter.calculate((byte)(0x02));
	}

	@Test
	public void testCalculateUnmarriedSubject()
	{
		double correctVal=24300000;		
		assertEquals(correctVal, NonTaxableIncomeCounter.calculate(EUnmarried), 0.0);
	}

	@Test
	public void testCalculateMarriedWithNoChildrenSubject()
	{
		double correctVal=26325000;		
		assertEquals(correctVal, NonTaxableIncomeCounter.calculate(EMarriedNoChildren), 0.0);
	}

	@Test
	public void testCalculateMarriedWithOneChildSubject()
	{
		double correctVal=283500000;		
		assertEquals(correctVal, NonTaxableIncomeCounter.calculate(EMarriedOneChild), 0.0);
	}

	@Test
	public void testCalculateMarriedWithTwoChildrenSubject()
	{
		double correctVal=30375000;		
		assertEquals(correctVal, NonTaxableIncomeCounter.calculate(EMarriedTwoChildren), 0.0);
	}

	@Test
	public void testCalculateMarriedWithThreeChildrenOrMoreSubject()
	{
		double correctVal=32400000;		
		assertEquals(correctVal, NonTaxableIncomeCounter.calculate(EMarriedThreeChildrenOrMore), 0.0);
	}
};

