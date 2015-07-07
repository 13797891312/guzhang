package com.android.futures.entity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;

/**
 * @author nanjingbiao
 * 
 */
public class RSIEntity {
	private ArrayList<Float> rsi;
	private ArrayList<Double> zhangfu = new ArrayList<Double>();

	public RSIEntity(List<OHLCEntity> OHLCData, int count) {
		rsi = new ArrayList<Float>();

		if (OHLCData != null && OHLCData.size() > 0) {
			for (int i = 0; i < OHLCData.size(); i++) {
				try {
					zhangfu.add(i,
							OHLCData.get(i).getClose()
									- OHLCData.get(i + 1).getClose());
				} catch (Exception e) {
					zhangfu.add(0d);
				}
			}
			for (int i = 0; i < OHLCData.size(); i++) {
				Double zhangNum = 0.0d;
				Double dieNum = 0.0d;
				try {

					for (int j = i; j <= i + count; j++) {
						if (zhangfu.get(j) >= 0) {
							zhangNum += zhangfu.get(j);
						} else {
							dieNum += zhangfu.get(j);
						}
					}
					dieNum = Math.abs(dieNum);
					Float dRsi = Float.parseFloat(new DecimalFormat("#")
							.format(100 - (100 / (1 + (zhangNum / count)
									/ (dieNum / count)))));
					dRsi = dRsi > 100 ? 100 : dRsi;
					rsi.add(dRsi);
				} catch (Exception e) {
					rsi.add(-1f);
				}
			}

			// OHLCEntity oHLCEntity = OHLCData.get(OHLCData.size() - 1);
			// double high = oHLCEntity.getHigh();
			// double low = oHLCEntity.getLow();
			//
			// for (int i = OHLCData.size() - 1; i >= 0; i--) {
			// if (i < OHLCData.size() - 1) {
			// oHLCEntity = OHLCData.get(i);
			// high = high > oHLCEntity.getHigh() ? high : oHLCEntity.getHigh();
			// low = low < oHLCEntity.getLow() ? low : oHLCEntity.getLow();
			// }
			// if (high != low) {
			// rSV = (oHLCEntity.getClose() - low) / (high - low) * 100;
			// }
			// if (i == OHLCData.size() - 1) {
			// k = rSV;
			// d = k;
			//
			// } else {
			// k = k * 2 / 3 + rSV / 3;
			// d = d * 2 / 3 + k / 3;
			// }
			// j = 3 * k - 2 * d;
			//
			// ks.add(k);
			// ds.add(d);
			// js.add(j);
			// }
			// for (int i = ks.size() - 1; i >= 0; i--) {
			// Ks.add(ks.get(i));
			// Ds.add(ds.get(i));
			// Js.add(js.get(i));
			// }
		}
	}

	public ArrayList<Float> getRsi() {
		return rsi;
	}
}
