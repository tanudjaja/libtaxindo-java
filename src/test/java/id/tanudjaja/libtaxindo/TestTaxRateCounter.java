package id.tanudjaja.libtaxindo;

import static org.junit.Assert.*;
import org.junit.*;
import org.junit.rules.ExpectedException;
import static org.hamcrest.CoreMatchers.*;

import static id.tanudjaja.libtaxindo.TaxRateCounter.*;

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

	@Test
	public void testCountTariffWithLessThanZeroTaxableIncome()
	{
		mExceptionRule.expect(IllegalArgumentException.class);
		mExceptionRule.expectMessage(INVALID_TAXABLE_INCOME);

		TaxRateCounter ctr=new TaxRateCounter(TAX_RULES);
		ctr.countTariff(-1);
	}

	@Test
	public void testCountTariffWithZeroTaxableIncome()
	{
		TaxRateCounter ctr=new TaxRateCounter(TAX_RULES);
		assertEquals(0, ctr.countTariff(0), 0.0);
	}

	@Test
	public void testCountTariffFallsInTheFirstLayer()
	{
		TaxRateCounter ctr=new TaxRateCounter(TAX_RULES);
		assertEquals(0.05 * 1000000, ctr.countTariff(1000000), 0.0);
	}

	@Test
	public void testCountTariffEqualsWithTheFirstMarker()
	{
		TaxRateCounter ctr=new TaxRateCounter(TAX_RULES);
		assertEquals(0.05 * 50000000, ctr.countTariff(50000000), 0.0);
	}

	@Test
	public void testCountTariffFallsInTheSecondLayer()
	{
		TaxRateCounter ctr=new TaxRateCounter(TAX_RULES);
		assertEquals((0.05 * 50000000) + (0.15 * 150000000), ctr.countTariff(200000000), 0.0);
	}

	@Test
	public void testCountTariffEqualsWithTheSecondMarker()
	{
		TaxRateCounter ctr=new TaxRateCounter(TAX_RULES);
		assertEquals((0.05 * 50000000) + (0.15 * 200000000), ctr.countTariff(250000000), 0.0);
	}

	@Test
	public void testCountTariffFallsInTheThirdLayer()
	{
		TaxRateCounter ctr=new TaxRateCounter(TAX_RULES);
		assertEquals((0.05 * 50000000) + (0.15 * 200000000) + (0.25 * 100000000), ctr.countTariff(350000000), 0.0);
	}

	@Test
	public void testCountTariffEqualsWithTheThirdMarker()
	{
		TaxRateCounter ctr=new TaxRateCounter(TAX_RULES);
		assertEquals((0.05 * 50000000) + (0.15 * 200000000) + (0.25 * 250000000), ctr.countTariff(500000000), 0.0);
	}

	@Test
	public void testCountTariffFallsInTheForthLayer()
	{
		TaxRateCounter ctr=new TaxRateCounter(TAX_RULES);
		assertEquals((0.05 * 50000000) + (0.15 * 200000000) + (0.25 * 250000000) + (0.30 * 100000000), ctr.countTariff(600000000), 0.0);
	}
};

