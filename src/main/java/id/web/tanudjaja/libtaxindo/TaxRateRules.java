
package id.web.tanudjaja.libtaxindo;

import java.util.Arrays;
import java.util.List;

public class TaxRateRules
{
	public final static String PARAMS_NULL="aIntervalMarkers and/or aTaxRates are/is null";
	public final static String PARAMS_ZERO_LENGTH="aIntervalMarkers and/or aTaxRates length are/is 0";
	public final static String INVALID_INTERVAL_MARKERS="At least one invalid interval marker found";
	public final static String INVALID_TAX_RATES="At least one invalid tax rate found";
	public final static String NO_VALID_INTERVAL_MARKERS="No valid interval markers found";
	public final static String INSUFFICIENT_TAX_RATES="The number of valid tax rates is less than the available intervals";

	private double [] mIntervalMarkers;
	private double [] mTaxRates;
    
	public TaxRateRules(double [] aIntervalMarkers, double [] aTaxRates)
	{
		// validate aIntervalMarkers pointer
		if(aIntervalMarkers==null || aTaxRates==null)
		{
			throw new NullPointerException(PARAMS_NULL);
		}
		if(aIntervalMarkers.length==0 || aTaxRates.length==0)
		{
			throw new IllegalArgumentException(PARAMS_ZERO_LENGTH);
		}

		// copy aIntervalMarkers to temp
		double [] temp=new double [aIntervalMarkers.length];
		for(int i=0; i<temp.length; i++)
		{
			temp[i]=aIntervalMarkers[i];
		}

		// validate temp - if non-valid value found, throw exception
		for(double d : temp)
		{
			if(d<=0)
			{
				throw new IllegalArgumentException(INVALID_INTERVAL_MARKERS);
			}
		}

		// validate temp - eliminate redundant value
		int invalidCount=0;
		for(int i=1; i<temp.length; i++)
		{
			for(int j=0; j<i; j++)
			{
				if(temp[i]==temp[j])
				{
					temp[i]=-1;
					invalidCount++;
					break;
				}
			}
		}

		// get the number of valid values
		int validCount=temp.length-invalidCount;
        
        	// never going to happen as at least the first value is guaranteed valid
//		if(validCount==0)
//		{
//			throw new InstantiationException(NO_VALID_INTERVAL_MARKERS);
//		}

		// sort temp
		Arrays.sort(temp);

		// copy temp to mIntervalMarkers
		mIntervalMarkers=new double [validCount];
		for(int i=0; i<validCount; i++)
		{
			mIntervalMarkers[i]=temp[i+invalidCount];
		}

/********** START PROCESSING TAX RATES ***********/

		// validate the number of tax rates - must be equal to number of interval markers + 1
		if(aTaxRates.length <= mIntervalMarkers.length)
		{
		    throw new IllegalArgumentException(INSUFFICIENT_TAX_RATES);
		}
		
		// validate aTaxRates - if non-valid value found, throw exception
		for(double d : aTaxRates)
		{
		    if(d<0)
		    {
		        throw new IllegalArgumentException(INVALID_TAX_RATES);
		    }
		}

		// copy temp to mTaxRates - only take as much as interval markers count + 1
		mTaxRates=new double [mIntervalMarkers.length+1];
		for(int i=0; i<mIntervalMarkers.length+1; i++)
		{
		    mTaxRates[i]=aTaxRates[i];
		}
	}
    
	public final double [] getIntervalMarkers() { return mIntervalMarkers; }
	public final double [] getTaxRates() { return mTaxRates; }
};

