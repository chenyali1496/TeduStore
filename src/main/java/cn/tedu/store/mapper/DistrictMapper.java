package cn.tedu.store.mapper;

import java.util.List;

import cn.tedu.store.entity.District;

public interface DistrictMapper {
	/**
	 * ����ʡ�����Ĵ��Ż�ȡ��ϸ����
	 * @param code
	 * @return ʡ��������ϸ����
	 */
	District findDistrictByCode(String code);

	//��ȡʡ���б�/ĳʡ���е��б�/ĳ�е������б�	
	/**
	 * ��ȡʡ���б�/ĳʡ���е��б�/ĳ�е������б�
	 * @param parent ������λ�Ĵ��ţ������Ҫ��ȡ
	 * @return ʡ�б�/���б�/���б�
	 */
	List<District>  getList(String parent);
}
