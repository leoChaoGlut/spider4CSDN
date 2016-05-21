package leo.mapper;

import java.util.List;

/**
 * @Description:
 * @Author leo
 * @Date 2016年5月21日 上午9:48:40
 * @Usage:
 */
public interface IBaseMapper<T> {
	List<T> selectAll(T t);

	T selectOne(T t);

	int insertOne(T t);

	int insertList(List<T> list);

	int updateOne(T t);

}
