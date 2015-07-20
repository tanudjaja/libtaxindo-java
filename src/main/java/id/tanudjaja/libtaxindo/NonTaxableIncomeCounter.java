
package id.tanudjaja.libtaxindo;

import static id.tanudjaja.libtaxindo.constants.MaritalStatus.*;

public final class NonTaxableIncomeCounter
{
	public final static String BAD_ENUM="Bad enumeration";

	private NonTaxableIncomeCounter()
	{
	}

	public static double calculate(boolean aMarried, short aNumberOfChild)
	{
		byte flag=0;
		flag |= (aMarried ? 1 : 0);
		flag = (byte)( (flag << 4) | ((aNumberOfChild > 3 ? 3 : aNumberOfChild) & 0xf) );
		return calculate(flag);
	}

	public static double calculate(byte aMaritalStatus)
	{
		switch(aMaritalStatus)
		{
			case EUnmarried: return 24300000;
			case EMarriedNoChildren: return 26325000;
			case EMarriedOneChild: return 283500000;
			case EMarriedTwoChildren: return 30375000;
			case EMarriedThreeChildrenOrMore: return 30375000;
			default: throw new IllegalArgumentException(BAD_ENUM);
		}
	}
};
