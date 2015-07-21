package id.web.tanudjaja.libtaxindo;

import static org.junit.Assert.*;
import org.junit.*;
import org.junit.rules.ExpectedException;
import static org.hamcrest.CoreMatchers.*;

import static id.web.tanudjaja.libtaxindo.TaxRateRules.*;

public class TestTaxRateRules
{
	@Rule
	public ExpectedException mExceptionRule=ExpectedException.none();

	@Test
	public void testConstructorWithNullIntervalMarkersOrTaxRates()
	{
		mExceptionRule.expect(NullPointerException.class);
		mExceptionRule.expectMessage(PARAMS_NULL);

		final double [] intervalMarkers=null;
		final double [] taxRates=null;

		TaxRateRules rules=new TaxRateRules(intervalMarkers, taxRates);
	}

	@Test
	public void testConstructorWithZeroLengthIntervalMarkersOrTexRates()
	{
		mExceptionRule.expect(IllegalArgumentException.class);
		mExceptionRule.expectMessage(PARAMS_ZERO_LENGTH);

		final double [] intervalMarkers=new double [] {};
		final double [] taxRates=new double [] {};

		TaxRateRules rules=new TaxRateRules(intervalMarkers, taxRates);
	}

	@Test
	public void testConstructorWithInvalidIntervalMarkers()
	{
		mExceptionRule.expect(IllegalArgumentException.class);
		mExceptionRule.expectMessage(INVALID_INTERVAL_MARKERS);
	
		final double [] intervalMarkers=new double []
		{
			50.0,
			10.0,
			20.0,
			40.0,
			25.0,
			0.0,
			-1.0
		};

		final double [] taxRates=new double [] { 0.5 };

		TaxRateRules rules=new TaxRateRules(intervalMarkers, taxRates);
	}
    
    @Test
    public void testConstructorWithInvalidTaxRates()
    {
        mExceptionRule.expect(IllegalArgumentException.class);
        mExceptionRule.expectMessage(INVALID_TAX_RATES);
        
        final double [] intervalMarkers=new double []
        {
            10.0,
            50.0,
            20.0,
            40.0,
            25.0,
        };
        
        final double [] taxRates=new double [] { 0.15, 0.50, -0.05, -0.25, 0.30, 0.50, 0.65 };
        
        TaxRateRules rules=new TaxRateRules(intervalMarkers, taxRates);
    }

	@Test
	public void testGetIntervalMarkersWithRedundantMarkers()
	{
		final double [] intervalMarkers=new double []
		{
			10.0,
			50.0,
			10.0,
			20.0,
			40.0,
			25.0,
			10.0,
			20.0
		};

		final double [] taxRates=new double [] { 0.05, 0.15, 0.25, 0.30, 0.50, 0.65 };

		TaxRateRules rules=new TaxRateRules(intervalMarkers, taxRates);
		double [] result=rules.getIntervalMarkers();

		assertEquals(5, result.length);
		assertEquals(10.0, result[0], 0.0);
		assertEquals(20.0, result[1], 0.0);
		assertEquals(25.0, result[2], 0.0);
		assertEquals(40.0, result[3], 0.0);
		assertEquals(50.0, result[4], 0.0);
	}
    
    @Test
    public void testGetTaxRates()
    {
        final double [] intervalMarkers=new double []
        {
            10.0,
            50.0,
            20.0,
            40.0,
        };
        
        final double [] taxRates=new double [] { 0.15, 0.50, 0.05, 0.25, 0.30, 0.50, 0.65 };
        
        TaxRateRules rules=new TaxRateRules(intervalMarkers, taxRates);
        double [] result=rules.getTaxRates();
        
        assertEquals(5, result.length);
        assertEquals(0.15, result[0], 0.0);
        assertEquals(0.50, result[1], 0.0);
        assertEquals(0.05, result[2], 0.0);
        assertEquals(0.25, result[3], 0.0);
        assertEquals(0.30, result[4], 0.0);
    }
};
