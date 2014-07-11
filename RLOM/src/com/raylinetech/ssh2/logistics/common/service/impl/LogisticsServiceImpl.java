package com.raylinetech.ssh2.logistics.common.service.impl;

import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.raylinetech.ssh2.logistics.common.config.PageConfig;
import com.raylinetech.ssh2.logistics.common.dao.LogisticsDao;
import com.raylinetech.ssh2.logistics.common.dao.SkuDao;
import com.raylinetech.ssh2.logistics.common.entity.Logistics;
import com.raylinetech.ssh2.logistics.common.entity.RLOrder;
import com.raylinetech.ssh2.logistics.common.entity.RLOrderItem;
import com.raylinetech.ssh2.logistics.common.entity.Sku;
import com.raylinetech.ssh2.logistics.common.service.LogisticsService;
import com.raylinetech.ssh2.logistics.common.service.PdfService;
import com.raylinetech.ssh2.logistics.common.util.SortUtil;

public class LogisticsServiceImpl implements LogisticsService {

	private LogisticsDao logisticsDao;

	private SkuDao skuDao;

	public SkuDao getSkuDao() {
		return skuDao;
	}

	public void setSkuDao(SkuDao skuDao) {
		this.skuDao = skuDao;
	}

	public LogisticsDao getLogisticsDao() {
		return logisticsDao;
	}

	public void setLogisticsDao(LogisticsDao logisticsDao) {
		this.logisticsDao = logisticsDao;
	}

	@Override
	public List<Logistics> getAllLogistics() {
		return logisticsDao.getAll();
	}

	@Override
	public List<RLOrder> allocationRLOrders(List<RLOrder> rlOrders) {
		List<RLOrder> result = new LinkedList<RLOrder>();
		// 将符合目标条件的先挑出来，并且将list相关的删除掉。
		// tobeupdate
		List<RLOrder> firsts = this.getFirstList(rlOrders);
		result.addAll(firsts);
		// 将剩余的进入一般逻辑里
		List<RLOrder> splitOrders = this.splitRLOrders(rlOrders);
		for (RLOrder rlOrder : splitOrders) {
			List<RLOrder> logid = this.addLogistics(rlOrder);
			result.addAll(logid);
		}
		return result;
	}

	private List<RLOrder> getFirstList(List<RLOrder> rlOrders) {
		List<RLOrder> result = new LinkedList<RLOrder>();
		for (RLOrder rlOrder : rlOrders) {
			if (rlOrder.getRlorderitems().size() == 1) {
				String skuno = rlOrder.getRlorderitems().get(0).getSku()
						.getSkuno();
				String account = rlOrder.getAccount();
				String market = rlOrder.getMarketplace();
				// 如果是指甲帖，高亮显示
				if (null != skuno
						&& PageConfig.isInZhijiaList(skuno.toUpperCase())) {
					System.out.println("zhijia");
					this.completRLOrder(rlOrder, skuno);
					//TOBEUPDATE 添加ALI
					if (!rlOrder.getMarketplace().equalsIgnoreCase(
							"aliexpress")) {
						if (!rlOrder.getRlorderitems().get(0).getQuantity().trim().equals("1")) {
							RLOrderItem item = new RLOrderItem(0,
									new Sku("RANDOM"), rlOrder.getRlorderitems().get(0).getQuantity().trim() + "", "");
							rlOrder.getRlorderitems().add(item);
							rlOrder.setSplitstatus(PageConfig.SPLIT_SYS_FIRST);
						}
					}
					result.add(rlOrder);
				} else if (null != skuno
						&& PageConfig.isInpijingList(skuno.toUpperCase())) {
					System.out.println("pijing");
					this.completRLOrder(rlOrder, skuno);
					result.add(rlOrder);
				} else {
					if (null != rlOrder.getRlorderitems()
							&& rlOrder.getRlorderitems().size() == 1) {
						if (null != market
								&& "AMAZON".equals(market.toUpperCase())) {
							if (PageConfig.isInAmazonList(skuno.toUpperCase())) {
								System.out.println("amazon"+ rlOrder.getOrdernumber());
								completRLOrder(rlOrder, skuno);
								result.add(rlOrder);
								// 设置logi
								// 设置sku
								// 设置amount
							}
						} else if (null != account
								&& "POVOS UK".equals(account.toUpperCase())) {
							if (PageConfig.isInPovosList(skuno.toUpperCase())) {
								System.out.println("povobuguahao");
								completRLOrder(rlOrder, skuno);
								result.add(rlOrder);
							}
						} else if (null != account
								&& "BEN UK".equals(account.toUpperCase())) {
							if (PageConfig.isInBenList(skuno.toUpperCase())) {
								System.out.println("benbuguahao");
								completRLOrder(rlOrder, skuno);
								result.add(rlOrder);
							}
						}
					}
				}
			}
		}
		rlOrders.removeAll(result);
		return result;
	}

	private void completRLOrder(RLOrder rlOrder, String skuno) {
		Sku sku = this.skuDao.find(skuno);
		rlOrder.setVendor(sku.getVendor());
		rlOrder.setItemname(sku.getName());
		rlOrder.setPinming(sku.getPinming());
		rlOrder.setSkuno(sku.getSkuno());
		rlOrder.setDescription(rlOrder.getRlorderitems().get(0)
				.getDescription());
		rlOrder.getRlorderitems().get(0).setSku(sku);
		if (null == rlOrder.getAmount()
				|| rlOrder.getAmount().trim().equals("")) {
			DecimalFormat df = new DecimalFormat("#.##");
			String amount = df.format(3.0 + Math.random() * 11);
			rlOrder.setAmount(amount);
		}

		rlOrder.setLogistics(new Logistics(PdfService.LOGISTICS_BJXBBGH));
	}

	/**
	 * 拆单逻辑
	 * 
	 * @param rlOrders
	 * @return
	 */
	private List<RLOrder> splitRLOrders(List<RLOrder> rlOrders) {
		// 将只有一件的直接返回
		List<RLOrder> resultList = new LinkedList<RLOrder>();
		// 将不止一件的放入处理集合中
		List<RLOrder> processList = new LinkedList<RLOrder>();
		for (RLOrder rlOrder : rlOrders) {
			// 将只有一件的直接返回,进入下一次循环
			if (rlOrder.getRlorderitems().size() == 1
					&& rlOrder.getRlorderitems().get(0).getQuantity().trim()
							.equals("1")) {
				if (null == rlOrder.getAmount()
						|| rlOrder.getAmount().equals("")) {
					DecimalFormat df = new DecimalFormat("#.##");
					String amount = df.format(3.0 + Math.random() * 11);
					rlOrder.setAmount(amount);
				}
				String skuno = rlOrder.getRlorderitems().get(0).getSku()
						.getSkuno();
				Sku sku = this.skuDao.find(skuno);
				rlOrder.getRlorderitems().get(0).setSku(sku);
				rlOrder.setVendor(sku.getVendor());
				rlOrder.setSkuno(sku.getSkuno());
				rlOrder.setItemname(sku.getName());
				rlOrder.setPinming(sku.getPinming());
				rlOrder.setDescription(rlOrder.getRlorderitems().get(0)
						.getDescription());
				resultList.add(rlOrder);
			} else {
				processList.add(rlOrder);
			}
		}
		// 得到待处理的processList
		for (RLOrder rlOrder : processList) {
			// 否则，首先按vendor分单
			List<RLOrderItem> items = rlOrder.getRlorderitems();
			// 用于存放单个vendor的所有item
			Map<String, List<RLOrderItem>> vendorMap = new TreeMap<String, List<RLOrderItem>>();
			for (RLOrderItem item : items) {
				String skuno = item.getSku().getSkuno();
				Sku sku = this.skuDao.find(skuno);
				item.setSku(sku);
				List<RLOrderItem> list = vendorMap.get(item.getSku()
						.getVendor());
				if (null == list) {
					list = new LinkedList();
					list.add(item);
					vendorMap.put(item.getSku().getVendor(), list);
				} else {
					vendorMap.get(item.getSku().getVendor()).add(item);
				}
			}
			// 至此得到所有供应商的所有item
			for (Entry<String, List<RLOrderItem>> entry : vendorMap.entrySet()) {
				Map<Integer, List<RLOrderItem>> areaMap = new TreeMap<Integer, List<RLOrderItem>>();
				// 得到单个供应商的所有item
				String vendor = entry.getKey();
				List<RLOrderItem> its = entry.getValue();
				for (RLOrderItem item : its) {
					List<RLOrderItem> list = areaMap.get(item.getSku()
							.getArea());
					if (null == list) {
						list = new LinkedList();
						list.add(item);
						areaMap.put(item.getSku().getArea(), list);
					} else {
						areaMap.get(item.getSku().getArea()).add(item);
					}
				}
				for (Entry<Integer, List<RLOrderItem>> areaEntry : areaMap
						.entrySet()) {
					int area = areaEntry.getKey();
					List<RLOrderItem> areaItems = areaEntry.getValue();
					// 如果只有一个sku这个sku的quantity为1，则直接返回，否则
					// 临时逻辑，如果size==1，先判断是否是目标格式，如果是设置
					if (areaItems.size() == 1
							&& areaItems.get(0).getQuantity().trim()
									.equals("1")) {
						RLOrder order = null;
						try {
							order = (RLOrder) rlOrder.clone();
						} catch (CloneNotSupportedException e) {
							e.printStackTrace();
						}
						order.setRlorderitems(areaItems);
						if (null == rlOrder.getAmount()
								|| rlOrder.getAmount().equals("")) {
							DecimalFormat df = new DecimalFormat("#.##");
							String amount = df.format(3.0 + Math.random() * 11);
							rlOrder.setAmount(amount);
						}
						String skuno = rlOrder.getRlorderitems().get(0)
								.getSku().getSkuno();
						Sku sku = this.skuDao.find(skuno);
						rlOrder.getRlorderitems().get(0).setSku(sku);
						rlOrder.setSkuno(sku.getSkuno());
						rlOrder.setVendor(sku.getVendor());
						rlOrder.setItemname(sku.getName());
						rlOrder.setPinming(sku.getPinming());
						rlOrder.setDescription(rlOrder.getRlorderitems().get(0)
								.getDescription());
						order.setSplitstatus(PageConfig.SPLIT_SYS_SPLIT);
						resultList.add(order);
					} else {
						// 将带电池的和不带电池的分开
						List<RLOrderItem> b = new LinkedList<RLOrderItem>();
						List<RLOrderItem> nb = new LinkedList<RLOrderItem>();
						for (RLOrderItem item : areaItems) {
							if (item.getSku().getBattery() == 0) {
								nb.add(item);
							} else {
								b.add(item);
							}
						}
						RLOrderItem[] bits = new RLOrderItem[b.size()];
						for (int i = 0; i < bits.length; i++) {
							bits[i] = b.get(i);
						}
						RLOrderItem[] nbits = new RLOrderItem[nb.size()];
						for (int i = 0; i < nbits.length; i++) {
							nbits[i] = nb.get(i);
						}
						List<RLOrderItem> allLists = new LinkedList<RLOrderItem>();
						if (bits != null && bits.length != 0) {
							SortUtil.QuickSortItem(bits, 0, bits.length);
							for (int i = bits.length - 1; i >= 0; i--) {
								allLists.add(bits[i]);
							}
						}
						if (nbits != null && nbits.length != 0) {
							SortUtil.QuickSortItem(nbits, 0, nbits.length);
							for (int i = nbits.length - 1; i >= 0; i--) {
								allLists.add(nbits[i]);
							}
						}
						// 关键逻辑，先从0开始取，当>1.8或者itemsize ==4的时候
						List<List<RLOrderItem>> itemLists = new LinkedList<List<RLOrderItem>>();
						while (allLists.size() > 0) {
							List<RLOrderItem> oneOrder = this
									.getOneOrder(allLists);
							RLOrder order = null;
							try {
								order = (RLOrder) rlOrder.clone();
							} catch (CloneNotSupportedException e) {
								e.printStackTrace();
							}
							order.setSplitstatus(PageConfig.SPLIT_SYS_SPLIT);
							// TOBEUPDATE如果平台不是ali，如果sku中含有指甲产品，则标记为红色。
							if (!order.getMarketplace().equalsIgnoreCase(
									"aliexpress")) {
								int zhijiaCount = 0;
								for (RLOrderItem item : oneOrder) {
									if (PageConfig.isInZhijiaList(item.getSku()
											.getSkuno().toUpperCase())) {
										order.setSplitstatus(PageConfig.SPLIT_SYS_FIRST);
										zhijiaCount += Integer.parseInt(item
												.getQuantity());
									}
								}
								if (zhijiaCount > 1) {
									RLOrderItem item = new RLOrderItem(0,
											new Sku("RANDOM"),
											zhijiaCount + "", "");
									oneOrder.add(item);
								}
							}
							// TOBEUPDATE

							order.setRlorderitems(oneOrder);
							order.setVendor(oneOrder.get(0).getSku()
									.getVendor());
							order.setItemname(oneOrder.get(0).getSku()
									.getName());
							order.setPinming(oneOrder.get(0).getSku()
									.getPinming());
							order.setSkuno(oneOrder.get(0).getSku().getSkuno());
							order.setDescription(oneOrder.get(0)
									.getDescription());
							if (null == rlOrder.getAmount()
									|| rlOrder.getAmount().trim().equals("")) {
								DecimalFormat df = new DecimalFormat("#.##");
								String amount = df
										.format(3.0 + Math.random() * 11);
								order.setAmount(amount);
							}
							resultList.add(order);
						}
					}
				}
			}
		}
		return resultList;
	}

	/**
	 * 从整个Items中获取一个order，并更新这个list
	 * 
	 * @param allLists
	 * @return
	 */
	private List<RLOrderItem> getOneOrder(List<RLOrderItem> allLists) {
		Map<String, RLOrderItem> oneOrder = new LinkedHashMap<String, RLOrderItem>();
		List<RLOrderItem> toDel = new LinkedList<RLOrderItem>();
		double weight = 0.0;

		// TODO
		for (int i = 0; i < allLists.size(); i++) {
			System.out.println(allLists.get(i).getSku().getSkuno() + "  "
					+ allLists.get(i).getSku().getBattery());
		}
		for (int i = 0; i < allLists.size(); i++) {
			int quantity = Integer.parseInt(allLists.get(i).getQuantity());
			while (quantity > 0) {
				double totalweight = weight
						+ allLists.get(i).getSku().getWeight();
				// 如果重量大于1.8kg，则结束内层循环，抓取allLists的下一件
				if (totalweight > 1.8) {
					break;
				} else {
					weight = totalweight;
					String skuno = allLists.get(i).getSku().getSkuno();
					RLOrderItem item = oneOrder.get(skuno);
					if (null == item) {
						// 如果sku数量小于4，则map增加一个条目，并且数量=1
						// 并且将allLists的元素i的quantity-1
						if (oneOrder.keySet().size() < 4) {
							try {
								item = (RLOrderItem) allLists.get(i).clone();
							} catch (CloneNotSupportedException e) {
								e.printStackTrace();
							}
							item.setQuantity("1");
							quantity = quantity - 1;
							allLists.get(i).setQuantity(quantity + "");
							// oneOrder.put(item.getSku().getVendor(), item);
							oneOrder.put(item.getSku().getSkuno(), item);
							if (quantity == 0) {
								toDel.add(allLists.get(i));
							}
							// 如果>4，则直接返回。
						} else {
							break;
						}
					} else {
						int qua = Integer.parseInt(oneOrder.get(skuno)
								.getQuantity());
						qua = qua + 1;
						oneOrder.get(skuno).setQuantity(qua + "");
						quantity = quantity - 1;
						allLists.get(i).setQuantity(quantity + "");
						// oneOrder.put(item.getSku().getVendor(), item);
						// oneOrder.put(item.getSku().getVendor(), item);
						if (quantity == 0) {
							toDel.add(allLists.get(i));
						}
					}
				}
			}
		}
		List<RLOrderItem> rlOrderItems = new LinkedList<RLOrderItem>();
		for (RLOrderItem rlOrderItem : oneOrder.values()) {
			System.out.println("one order values are "
					+ rlOrderItem.getSku().getSkuno() + "  "
					+ rlOrderItem.getSku().getBattery());
			rlOrderItems.add(rlOrderItem);
		}
		allLists.removeAll(toDel);
		return rlOrderItems;
	}

	/**
	 * 关键方法，分拆order，按照规则选择不同的物流方式 相同的物流方式封装成同一个对象。 之后的版本可能涉及到拆单的问题，增加一个标签吧
	 * 
	 * @param rlOrder
	 * @return
	 */
	private List<RLOrder> addLogistics(RLOrder rlOrder) {
		// tobeupdate
		List<RLOrder> results = new LinkedList<RLOrder>();
		String vendor = rlOrder.getVendor();
		String postalCode = rlOrder.getPostalcode();
		String guojia = rlOrder.getGuojia();
		List<RLOrderItem> items = rlOrder.getRlorderitems();
		// 存放logid和相应item的键值对，用于封装rlorder
		Map<Integer, List> orderMap = new LinkedHashMap<Integer, List>();
		String skuno = items.get(0).getSku().getSkuno();
		if (skuno.indexOf("--") > 0) {
			skuno = skuno.substring(0, skuno.indexOf(LogisticsService.BZ));
		}
		Sku sku = this.skuDao.find(skuno);
		if (null == sku) {
			List no = orderMap.get(0);
			Object[] objs = { items.get(0), null };
			System.out.println("物流选择了第" + 1123);
			// 如果为空，则表示还没有元素在该键值对中，创建新list并且put进入map中
			if (null == no) {
				no = new LinkedList();
				no.add(objs);
				orderMap.put(0, no);
			} else {
				orderMap.get(0).add(objs);
			}
		} else {
			int area = sku.getArea();
			int battery = sku.getBattery();
			int promotion = sku.getPromotion();
			String quantity = items.get(0).getQuantity();
			String account = rlOrder.getAccount();
			double weight = 0.0;
			for (RLOrderItem item : items) {
				String skuno1 = item.getSku().getSkuno();
				if (skuno1.indexOf(LogisticsService.BZ) > 0) {
					skuno1 = skuno1.substring(0,
							skuno1.indexOf(LogisticsService.BZ));
				}
				Sku sku1 = this.skuDao.find(skuno);
				weight = weight + Double.parseDouble(item.getQuantity())
						* sku1.getWeight();

			}
			int logisticsId = this.getlogisticsId(vendor, postalCode, guojia,
					area, battery, weight, promotion, quantity, account);
			System.out.println("物流选择了第" + logisticsId);
			List list = orderMap.get(logisticsId);
			Object[] objs = { items.get(0), sku };
			// 如果为空，则表示还没有元素在该键值对中，创建新list并且put进入map中
			if (null == list) {
				list = new LinkedList();
				list.add(objs);
				orderMap.put(logisticsId, list);
			} else {
				orderMap.get(logisticsId).add(objs);
			}
		}
		// tobeupdate
		// map<logid,list>
		// 操作map，得到rlORder的list
		results = this.packageMapToList(rlOrder, orderMap);
		return results;
	}

	/**
	 * 将map中的对象和原始的rlorder封装成多个对象
	 * 
	 * @param rlOrder
	 * @param orderMap
	 * @return
	 */
	private List<RLOrder> packageMapToList(RLOrder rlOrder,
			Map<Integer, List> orderMap) {
		List<RLOrder> orders = new LinkedList<RLOrder>();

		for (Entry<Integer, List> entry : orderMap.entrySet()) {
			int key = entry.getKey();
			List value = entry.getValue();
			// 将相同集合里的所有所有物料重量判断
			// 如果重量超过1.8，则分拆成两个order
			// RLOrder order = null;
			// try {
			// order = rlOrder.clone();
			// Logistics logi = new Logistics(key,"","");
			// // logi.setId(key);
			// order.setLogistics(logi);
			// } catch (CloneNotSupportedException e) {
			// e.printStackTrace();
			// }
			if (key != 0) {
				Logistics logi = new Logistics(key, "", "");
				rlOrder.setLogistics(logi);
			} else {
				Logistics logi = new Logistics(999, "", "");
				rlOrder.setLogistics(logi);
			}
			orders.add(rlOrder);
		}
		return orders;
	}

	private int getlogisticsId(String vendor, String postalCode, String guojia,
			int area, int battery, double weight, int promotion,
			String quantity, String account) {
		int[] datas = { 0, 0, 0, 0, 0, 0, 0, 0 };
		if ("CDGV11".equalsIgnoreCase(vendor)) {
			datas[0] = 1;
		}
		datas[1] = area;
		datas[2] = battery;
		if ("美国".equals(guojia)) {
			datas[3] = 1;
		} else if ("俄罗斯".equals(guojia)) {
			datas[3] = 2;
		} else if ("英国".equals(guojia)) {
			datas[3] = 3;
		}

		String[] pos = { "GY", "IV", "HS", "KA27", "KA28", "KW", "PA20",
				"PA21", "PA22", "PA23", "PA24", "PA25", "PA26", "PA27", "PA28",
				"PA29", "PA30", "PA31", "PA32", "PA33", "PA34", "PA35", "PA36",
				"PA37", "PA38", "PA39", "PA60", "PA61", "PA62", "PA63", "PA64",
				"PA65", "PA66", "PA67", "PA68", "PA69", "PA70", "PA71", "PA72",
				"PA73", "PA74", "PA75", "PA76", "PA77", "PA78", "PH17", "PH18",
				"PH19", "PH20", "PH21", "PH22", "PH23", "PH24", "PH25", "PH26",
				" PH30", "PH31", "PH32", "PH33", "PH34", "PH35", "PH36",
				"PH37", "PH38", " PH39", "PH40", " PH41", "PH42", " PH43",
				"PH 44", "PH49", "PH50", "IM", "BT", "JE", "ZE", "TR21",
				"TR22", "TR23", "TR24", "TR25" };
		boolean flag = false;
		for (String string : pos) {
			if (postalCode.toUpperCase().contains(string)) {
				flag = true;
			}
		}
		if (flag) {
			datas[4] = 1;
		}

		if (weight >= 0.3) {
			datas[5] = 1;
		}

		datas[6] = promotion;
		if ("Povos Uk".equalsIgnoreCase(account)
				|| "Povos Us".equalsIgnoreCase(account)) {
			datas[7] = 1;
		}
		StringBuilder sb = new StringBuilder();
		for (int i : datas) {
			sb.append(i);
		}
		return this.getLogistics(datas);
	}

	private int getLogistics(int[] datas) {
		StringBuilder sb = new StringBuilder();
		for (int i : datas) {
			sb.append(i);
		}
		if (datas[0] == 1) {
			if (datas[3] == 1) {
				return 13;
			} else if (datas[3] == 2) {
				return 19;
			} else if (datas[3] == 3) {
				if (datas[4] == 0) {
					return 11;
				} else {
					return 7;
				}
			} else if (datas[3] == 0) {
				return 7;
			}
		} else {
			if (datas[1] == 1) {
				if (datas[2] == 1) {
					if (datas[3] == 1) {
						return 10;
					} else if (datas[3] == 2) {
						return 17;
					} else if (datas[3] == 3) {
						if (datas[4] == 0) {
							return 10;
						} else {
							return 17;
						}
					} else if (datas[3] == 0) {
						return 17;
					}
				} else {
					if (datas[3] == 1) {
						return 14;
					} else if (datas[3] == 2) {
						return 21;
					} else if (datas[3] == 3) {
						if (datas[4] == 0) {
							if (datas[5] == 0) {
								if (datas[6] == 0) {
									return 1;
								} else {
									if (datas[7] == 0) {
										return 1;
									} else {
										return 2;
									}
								}
							} else {
								return 10;
							}
						} else {
							if (datas[6] == 0) {
								return 1;
							} else {
								if (datas[7] == 0) {
									return 1;
								} else {
									return 2;
								}
							}
						}
					} else if (datas[3] == 0) {
						return 1;
					}
				}
			} else if (datas[1] == 3) {
				if (datas[2] == 1) {
					if (datas[3] == 1) {
						return 12;
					} else if (datas[3] == 2) {
						return 16;
					} else if (datas[3] == 3) {
						if (datas[4] == 0) {
							return 12;
						} else {
							return 16;
						}
					} else if (datas[3] == 0) {
						return 16;
					}
				} else {
					if (datas[3] == 1) {
						return 13;
					} else if (datas[3] == 2) {
						return 20;
					} else if (datas[3] == 3) {
						if (datas[4] == 0) {
							if (datas[5] == 1) {
								return 12;
							}
						} else {
							return 3;
						}
						if (datas[5] == 0) {
							return 3;
						}
					} else if (datas[3] == 0) {
						return 3;
					}
				}
			} else if (datas[1] == 4) {
				if (datas[2] == 1) {
					if (datas[3] == 1) {
						return 11;
					} else if (datas[3] == 2) {
						return 15;
					} else if (datas[3] == 3) {
						if (datas[4] == 0) {
							return 11;
						} else {
							return 15;
						}
					} else if (datas[3] == 0) {
						return 15;
					}
				} else {
					if (datas[3] == 1) {
						return 13;
					} else if (datas[3] == 2) {
						return 19;
					} else if (datas[3] == 3) {
						if (datas[4] == 0) {
							if (datas[5] == 1) {
								return 11;
							}
						} else {
							return 7;
						}
						if (datas[5] == 0) {
							return 7;
						}
					} else if (datas[3] == 0) {
						return 7;
					}
				}
			}
		}

		return 0;
	}

	public static void main(String[] args) {
		// ArrayList<RLOrder> orders = new ArrayList<RLOrder>();
		// RLOrder o = new RLOrder();
		// RLOrderItem item = new RLOrderItem();
		// List<RLOrderItem> lis = new ArrayList<RLOrderItem>();
		// lis.add(item);
		// o.setAccount("a");
		// o.setRlorderitems(lis);
		// try {
		// RLOrder o2 = (RLOrder)o.clone();
		// } catch (CloneNotSupportedException e) {
		// e.printStackTrace();
		// }
		//
		// orders.add(o);
		// RLOrder b = o;
		// b.setAccount("b");
		// orders.add(b);
		// for (RLOrder rlOrder : orders) {
		// System.out.println(rlOrder.getAccount());
		// }
		// String a = "fljalsdfoiuadf-falksjdf-sdfjladksasdflkjsdf";
		// if(a.indexOf("--")>0){
		// a= a.substring(0, a.indexOf("--"));
		// }
		// System.out.println(a);
		// System.out.println("David Uk".equalsIgnoreCase("david uk"));
		// List<Integer> l = new ArrayList();
		// int[] a = {1,2,3,4,5};
		// for (int i = a.length-1; i >=0; i--) {
		// l.add(a[i]);
		// }
		// System.out.println(l);
		// List<Integer> ll = new ArrayList();
		// for (int i : l) {
		// System.out.println(i);
		// ll.add(i);
		// }
		// System.out.println(l);
		// System.out.println(ll);
		// l.removeAll(ll);
		// System.out.println(l);
		// System.out.println(ll);
		DecimalFormat df = new DecimalFormat("#.##");
		String amount = df.format(3.0 + Math.random() * 11);
		System.out.println(amount);

		// Iterator<Integer> itr = l.iterator();
		// while (itr.hasNext()) {
		// int i = itr.next();
		// System.out.println(i);
		// l.remove(i);
		//
		// }
		//
		// for (int i = 0; i < 5; i++) {
		// System.out.println("进入内层循环");
		// for (int j = 0; j < 5; j++) {
		// if(j==3){
		// continue;
		// }
		// System.out.println(j);
		// }
		// System.out.println("------");
		// }
		//
		// while(l.size()>0){
		// int i = (Integer) l.remove(0);
		// System.out.println(i);
		// }
	}
	// /**
	// * 关键方法，分拆order，按照规则选择不同的物流方式
	// * 相同的物流方式封装成同一个对象。
	// * 之后的版本可能涉及到拆单的问题，增加一个标签吧
	// * @param rlOrder
	// * @return
	// */
	// private List<RLOrder> splitRLOrders(RLOrder rlOrder) {
	// //tobeupdate
	// List<RLOrder> results= new ArrayList<RLOrder>();
	// String vendor = rlOrder.getVendor();
	// String postalCode = rlOrder.getPostalcode();
	// String guojia = rlOrder.getGuojia();
	// List<RLOrderItem> items = rlOrder.getRlorderitems();
	// //存放logid和相应item的键值对，用于封装rlorder
	// Map<Integer, List> orderMap = new HashMap<Integer, List>();
	// for (RLOrderItem item : items) {
	// String skuno = item.getSkuno();
	// Sku sku = this.skuDao.find(skuno);
	// if(null == sku){
	// List no = orderMap.get(0);
	// Object[] objs = {item,null};
	// //如果为空，则表示还没有元素在该键值对中，创建新list并且put进入map中
	// if(null==no){
	// no = new ArrayList();
	// no.add(objs);
	// orderMap.put(0, no);
	// }else{
	// orderMap.get(0).add(objs);
	// }
	// }
	// int area = sku.getArea();
	// int battery = sku.getBattery();
	// int promotion = sku.getPromotion();
	// String quantity = item.getQuantity();
	// String account = rlOrder.getAccount();
	// //获取重量
	// double weight = Double.parseDouble(item.getQuantity()) * sku.getWeight();
	// //多次查询会导致性能下降
	// //有时间处理一下，升级的时候应该改版成为Mybates，有助于提高系统总效率。
	// //一定要研究
	// int logisticsId =
	// this.getlogisticsId(vendor,postalCode,guojia,area,battery,weight,promotion,quantity,account);
	// List list = orderMap.get(logisticsId);
	// Object[] objs = {item,sku};
	// //如果为空，则表示还没有元素在该键值对中，创建新list并且put进入map中
	// if(null==list){
	// list = new ArrayList();
	// list.add(objs);
	// orderMap.put(logisticsId, list);
	// }else{
	// orderMap.get(logisticsId).add(objs);
	// }
	// }
	// //tobeupdate
	// //map<logid,list>
	// //操作map，得到rlORder的list
	// results = this.packageMapToList(rlOrder,orderMap);
	// return results;
	// }
}
