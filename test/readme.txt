总结：

方法一：
1. 采用stringBuilder将文件转换为字符串。
2. 然后用string.replace()讲单词替换为空。
3. (替换前的总长度-替换后的总长度)/单词长度，得到单词出现的频率。
4. 实际测试中发现文件过大时，stringBuilder的容量扩充失败。需要加以限制。
5. 设置一个stringBuilder的容量上限，超出上限就先计算当前的内容单词出现频率。然后清空stringBuilder，再继续读取文件，如此循环。
6. 此方法处理1G文件耗时大约20000毫秒。

寻找优化方案
方法二：
1. 采用byteBuffer读入整个文件。
2. 自己写了一个简单的算法：挨个对比byteBuffer和单词中的每个byte（详见代码）。
3. 此方法处理1G文件耗时大约200毫秒。大大提高了效率。

改进计划：
1. 方法二没有考虑语言编码，目前只对ascII有效。
2. 文件如果更大，内存可能不够。需要用MappedByteBuffer映射到虚拟内存。
