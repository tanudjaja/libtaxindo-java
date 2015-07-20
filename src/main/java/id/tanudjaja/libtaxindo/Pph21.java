
package id.tanudjaja.libtaxindo;

import id.tanudjaja.libtaxindo.constants.MaritalStatus;

public class Pph21
{
	private final double mGajiPokok;
	private final double mTkp;
	private final double mBonusThr;
	private final byte  mStatusWp;
	private final double mPension;

	public Pph21(double aGajiPokok, double aTkp, double aBonusThr, byte aStatusWp, double aPension)
	{
		mGajiPokok=aGajiPokok;
		mTkp=aTkp;
		mBonusThr=aBonusThr;
		mStatusWp=aStatusWp;
		mPension=aPension;
	}

	public double getPtkp()
	{
		return NonTaxableIncomeCounter.calculate(mStatusWp);
	}

	private double countJkk()
	{
		return 0.024 * mGajiPokok;
	}

	private double countJkm()
	{
		return 0.03 * mGajiPokok;
	}

	private double countBruto()
	{
		return mGajiPokok+mTkp+mBonusThr+countJkk()+countJkm();
	}

	private double countBiayaJabatan()
	{
		return Math.min(500000, 0.05*countBruto());
	}

	private double countJht()
	{
		return 0.02 * mGajiPokok;
	}

	private double countNetto()
	{
		double temp1=countBiayaJabatan()+countJht()+mPension;

		return countBruto()-temp1;
	}

	private double countPkp()
	{
		double temp2=12*countNetto();

		return Math.floor(temp2-getPtkp());
	}

	private double countTariff()
	{
        	return new TaxRateCounter(new TaxRate2013Rules()).calculate(countPkp());
	}

	public double countPph21()
	{
		double temp3=countTariff() * countPkp();

		return temp3 / 12.0;
	}

};
