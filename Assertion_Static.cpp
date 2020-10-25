#define _ASSERT_GLUE(a, b)  a ## b
#define ASSERT_GLUE(a, b)   _ASSERT_GLUE(a, b)

#define STATIC_ASSERT(expr) \
    enum    \
    {   \
        ASSERT_GLUE(g_assert_fail_, __LINE__)   \
            == 1 / (int) (!!(expr)) \
    }


struct NeedsToBe128Bytes
{
    U32 m_a;
    F32 m_b;
};

STATIC_ASSERT(sizeof(NeedsToBe128Bytes) == 128);
