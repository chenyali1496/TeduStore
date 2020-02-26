package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.District;

/**
 * ʡ����������ҵ���
 * @author Administrator
 *
 */
public interface IDistrictService {
	/**
	 * ����ʡ�����Ĵ��Ż�ȡ��ϸ����
	 * @param code
	 * @return ʡ��������ϸ����
	 */
	District getDistrictByCode(String code);
	/**
	 * ��ȡʡ���б�/ĳʡ���е��б�/ĳ�е������б�
	 * @param parent ������λ�Ĵ��ţ������Ҫ��ȡ
	 * @return ʡ�б�/���б�/���б�
	 */
	List<District>  getList(String parent);
}
