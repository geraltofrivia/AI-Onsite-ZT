// https://github.com/pagehelper/Mybatis-PageHelper/tree/4db191cd241904e8c63cc9c2413a4ca74c2fd662/src/test/java/com/github/pagehelper/test/pagesize/PageSizeLessThenOrEqualZeroTest.java#L45-L66
public class TempClass {
    @Test
    public void testWithStartPage() {
        SqlSession sqlSession = MybatisHelper.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        try {
            //pageSize=0,这时候相当于用分页插件求count
            PageHelper.startPage(1, 0);
            List<User> list = userMapper.selectAll();
            PageInfo<User> page = new PageInfo<User>(list);
            assertEquals(0, list.size());
            assertEquals(183, page.getTotal());

            //limit<0的时候同上
            PageHelper.startPage(1, -100);
            list = userMapper.selectAll();
            page = new PageInfo<User>(list);
            assertEquals(0, list.size());
            assertEquals(183, page.getTotal());
        } finally {
            sqlSession.close();
        }
    }

}