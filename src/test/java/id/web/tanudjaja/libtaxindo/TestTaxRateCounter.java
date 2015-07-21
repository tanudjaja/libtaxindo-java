package id.web.tanudjaja.libtaxindo;

import static org.junit.Assert.*;
import org.junit.*;
import org.junit.rules.ExpectedException;
import static org.hamcrest.CoreMatchers.*;

import static id.web.tanudjaja.libtaxindo.TaxRateCounter.*;

public class TestTaxRateCounter
{
	@Rule
	public ExpectedException mExceptionRule=ExpectedException.none();

	private static TaxRateRules TAX_RULES=new TaxRateRules
	(
		new double []
		{
			50000000.0,
			250000000.0,
			500000000.0
		},
		new double []
		{
			0.05,
			0.15,
			0.25,
			0.30
		}
	);

	private static TaxRateRules TAX_RULES_2=new TaxRateRules
	(
		new double []
		{
			50000000.0
		},
		new double []
		{
			0.05,
			0.15,
		}
	);

	@Test
	public void testCalculate2FallsInTheFirstLayer()
	{
		double correctVal=0.05 * 1000000;
		TaxRateCounter ctr=new TaxRateCounter(TAX_RULES_2);
		assertEquals(correctVal, ctr.calculate(1000000), 0.0);
	}

	@Test
	public void testCalculate2EqualsWithTheFirstMarker()
	{
		double correctVal=0.05 * 50000000;
		TaxRateCounter ctr=new TaxRateCounter(TAX_RULES_2);
		assertEquals(correctVal, ctr.calculate(50000000), 0.0);
	}

	@Test
	public void testCalculate2FallsInTheSecondLayer()
	{
		double correctVal=(0.05 * 50000000) + (0.15 * 100000000);
		TaxRateCounter ctr=new TaxRateCounter(TAX_RULES_2);
		assertEquals(correctVal, ctr.calculate(150000000), 0.0);
	}

	@Test
	public void testCalculateWithLessThanZeroTaxableIncome()
	{
		mExceptionRule.expect(IllegalArgumentException.class);
		mExceptionRule.expectMessage(INVALID_TAXABLE_INCOME);

		TaxRateCounter ctr=new TaxRateCounter(TAX_RULES);
		ctr.calculate(-1);
	}

	@Test
	public void testCalculateWithZeroTaxableIncome()
	{
		double correctVal=0;		
		TaxRateCounter ctr=new TaxRateCounter(TAX_RULES);
		assertEquals(correctVal, ctr.calculate(0), 0.0);
	}

	@Test
	public void testCalculateFallsInTheFirstLayer()
	{
		double correctVal=0.05 * 1000000;
		TaxRateCounter ctr=new TaxRateCounter(TAX_RULES);
		assertEquals(correctVal, ctr.calculate(1000000), 0.0);
	}

	@Test
	public void testCalculateEqualsWithTheFirstMarker()
	{
		double correctVal=0.05 * 50000000;
		TaxRateCounter ctr=new TaxRateCounter(TAX_RULES);
		assertEquals(correctVal, ctr.calculate(50000000), 0.0);
	}

	@Test
	public void testCalculateFallsInTheSecondLayer()
	{
		double correctVal=(0.05 * 50000000) + (0.15 * 150000000);
		TaxRateCounter ctr=new TaxRateCounter(TAX_RULES);
		assertEquals(correctVal, ctr.calculate(200000000), 0.0);
	}

	@Test
	public void testCalculateEqualsWithTheSecondMarker()
	{
		double correctVal=(0.05 * 50000000) + (0.15 * 200000000);
		TaxRateCounter ctr=new TaxRateCounter(TAX_RULES);
		assertEquals(correctVal, ctr.calculate(250000000), 0.0);
	}

	@Test
	public void testCalculateFallsInTheThirdLayer()
	{
		double correctVal=(0.05 * 50000000) + (0.15 * 200000000) + (0.25 * 100000000);
		TaxRateCounter ctr=new TaxRateCounter(TAX_RULES);
		assertEquals(correctVal, ctr.calculate(350000000), 0.0);
	}

	@Test
	public void testCalculateEqualsWithTheThirdMarker()
	{
		double correctVal=(0.05 * 50000000) + (0.15 * 200000000) + (0.25 * 250000000);
		TaxRateCounter ctr=new TaxRateCounter(TAX_RULES);
		assertEquals(correctVal, ctr.calculate(500000000), 0.0);
	}

	@Test
	public void testCalculateFallsInTheForthLayer()
	{
		double correctVal=(0.05 * 50000000) + (0.15 * 200000000) + (0.25 * 250000000) + (0.30 * 100000000);
		TaxRateCounter ctr=new TaxRateCounter(TAX_RULES);
		assertEquals(correctVal, ctr.calculate(600000000), 0.0);
	}
};

