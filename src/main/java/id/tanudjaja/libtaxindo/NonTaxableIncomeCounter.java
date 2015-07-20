
package id.tanudjaja.libtaxindo;

import id.tanudjaja.libtaxindo.constants.MaritalStatus;

public final class NonTaxableIncomeCounter
{
	public final static String BAD_ENUM="Bad enumeration";
	public final static String NEGATIVE_NUM_OF_CHILD="Number of child is less than 0";

	private NonTaxableIncomeCounter()
	{
	}

	public static double calculate(boolean aMarried, int aNumberOfChild)
	{
		if(aNumberOfChild < 0)
		{
			throw new IllegalArgumentException(NEGATIVE_NUM_OF_CHILD);
		}

		byte flag=0;
		flag |= (aMarried ? 1 : 0);
		flag = (byte)( (flag << 4) | ((aNumberOfChild > 3 ? 3 : aNumberOfChild) & 0xf) );
		return calculate(flag);
	}

	public static double calculate(byte aMaritalStatus)
	{
		switch(aMaritalStatus)
		{
			case MaritalStatus.UNMARRIED: return 24300000;
			case MaritalStatus.MARRIED_NO_CHILDREN: return 26325000;
			case MaritalStatus.MARRIED_ONE_CHILD: return 283500000;
			case MaritalStatus.MARRIED_TWO_CHILDREN: return 30375000;
			case MaritalStatus.MARRIED_THREE_OR_MORE_CHILDREN: return 32400000;
			default: throw new IllegalArgumentException(BAD_ENUM);
		}
	}
};
