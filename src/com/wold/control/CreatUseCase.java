package com.wold.control;

import java.util.List;
import java.util.Map;

import com.wold.pojo.Rule;
/**
 * 由提取出来的规则生成正交用例
 * @author WOLD
 *
 */
public class CreatUseCase {
	private String label5show;
	private String usecase;
	/**
	 * 
	 * @param listrule 规则
	 * @param map <>
	 * @param special 是否超过10位数
	 * @param ruletable 
	 */
	public void create(List<Rule> listrule, Map<Integer, Integer> map, Boolean special, String ruletable) {
		label5show = "";
		usecase = "";
		for (Rule rule : listrule) {
			label5show += rule.getName() + " ";
		}
		if (!special) {
			String[] a = ruletable.split("\n");
			for (String string : a) {
				char[] arr = string.toCharArray();
				for (int i = 0; i < listrule.size(); i++) {
					Rule r = listrule.get(i);
					usecase += r.getList().get(arr[i] - '0')+" ";
				}
				usecase += "\n";
			}
		} else {
			int sum=0;
			for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
				if(entry.getKey()<10) {
					sum+=entry.getValue();
				}
			}
			String[] a = ruletable.split("\n");
			for (String string : a) {
				char[] arr = string.toCharArray();
				int i=0;
				for (i = 0; i < sum; i++) {
					Rule r = listrule.get(i);
					usecase += r.getList().get(arr[i] - '0')+" ";
				}
				for(int j=sum;j<arr.length;j+=2,i++) {
					String temp="";
					if(arr[j]!=' ') {
						temp=String.valueOf(new char[] {arr[j],arr[j+1]});
					}else {
						temp=String.valueOf(arr[j+1]);
					}
					Rule r = listrule.get(i);
					usecase +=r.getList().get(Integer.valueOf(temp))+" ";
				}
				usecase += "\n";
			}
		}
	}

	public String getLabel5show() {
		return label5show;
	}

	public String getUsecase() {
		return usecase;
	}
}
