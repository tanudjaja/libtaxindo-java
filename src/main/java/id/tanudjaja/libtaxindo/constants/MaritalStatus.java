
package id.tanudjaja.libtaxindo.constants;

public final class MaritalStatus
{
	// 4 msb indicates the marital status (0 for single, 1 for married)
	// 4 lsb indicates the number of children

	public final static byte EUnmarried=0x00;
	public final static byte EMarriedNoChildren=0x10;
	public final static byte EMarriedOneChild=0x11;
	public final static byte EMarriedTwoChildren=0x12;
	public final static byte EMarriedThreeChildrenOrMore=0x13;
};

