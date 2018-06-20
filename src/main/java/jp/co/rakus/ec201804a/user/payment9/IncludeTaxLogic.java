package jp.co.rakus.ec201804a.user.payment9;

/**
 * 消費税計算用ロジック.
 * 
 * @author KouheiTaguchi
 *
 */
public class IncludeTaxLogic {
	/** 税 */
	private Integer tax;

	public Integer getTax() {
		return tax;
	}

	public void setTax(Integer tax) {
		this.tax = tax;
	}

	/**
	 * 消費税のみ算出するメソッド.
	 * 
	 * @param excludeTax
	 *            税別の商品価格
	 * @return 消費税のみ戻す
	 */
	public Integer calcTax(Integer excludeTax) {
		setTax((int) (excludeTax * 0.08));
		return tax;
	}

	/**
	 * 税込み価格を算出するメソッド.
	 * 
	 * @param excludeTax
	 *            税別の商品価格
	 * @return 税込み価格を戻す
	 */
	public Integer includeTax(Integer excludeTax) {
		setTax((int) (excludeTax * 1.08));
		return tax;
	}

}
