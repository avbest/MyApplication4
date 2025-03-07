import React from 'react';
import { View, Text, StyleSheet, ScrollView, TouchableOpacity } from 'react-native';
import Icon from 'react-native-vector-icons/MaterialIcons';

const ProfileItem = ({ icon, title, subtitle, onPress }) => (
  <TouchableOpacity style={styles.item} onPress={onPress}>
    <View style={styles.itemLeft}>
      <Icon name={icon} size={24} color="#4A6495" />
      <View style={styles.itemTextContainer}>
        <Text style={styles.itemTitle}>{title}</Text>
        {subtitle && <Text style={styles.itemSubtitle}>{subtitle}</Text>}
      </View>
    </View>
    <Icon name="chevron-right" size={24} color="#666" />
  </TouchableOpacity>
);

const ProfilePage = () => {
  const navigation = useNavigation();

  const handleItemPress = (itemName) => {
    if (itemName === '课程表') {
      navigation.navigate('CourseSettingPage');
    }
    // 其他功能的导航逻辑可以在这里添加
  };

  return (
    <View style={styles.container}>
      <ScrollView style={styles.content}>
        <ProfileItem 
          icon="account-circle" 
          title="点击登录" 
          subtitle="不登录也可以使用校内功能哦"
        />
        <ProfileItem 
          icon="local-library" 
          title="图书馆" 
          subtitle="维护中..."
        />
        <ProfileItem 
          icon="directions-bus" 
          title="校巴" 
        />
        <ProfileItem 
          icon="event-note" 
          title="课程表" 
          onPress={() => handleItemPress('课程表')}
        />
        <ProfileItem 
          icon="credit-card" 
          title="E-卡" 
        />
        <ProfileItem 
          icon="directions-run" 
          title="运动" 
        />
        <ProfileItem 
          icon="star" 
          title="成绩" 
        />
        <ProfileItem 
          icon="assessment" 
          title="给分" 
        />
        <ProfileItem 
          icon="school" 
          title="淘课啦课程评价" 
          subtitle="WHU课程评价收集与查询系统"
        />
        <ProfileItem 
          icon="chat" 
          title="SoruxGPT" 
          subtitle="WHU共享GPT节点"
        />
        <ProfileItem 
          icon="folder" 
          title="OpenWHU - WHU课代表计划" 
          subtitle="武汉大学课程资料整理"
        />
        <ProfileItem 
          icon="share" 
          title="WHU开源资料" 
          subtitle="共享资料，大多为历年试卷、PPT等"
        />
      </ScrollView>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#F5F5F5',
  },
  content: {
    flex: 1,
  },
  item: {
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'space-between',
    paddingVertical: 16,
    paddingHorizontal: 16,
    backgroundColor: '#FFF',
    marginBottom: 1,
  },
  itemLeft: {
    flexDirection: 'row',
    alignItems: 'center',
  },
  itemTextContainer: {
    marginLeft: 16,
  },
  itemTitle: {
    fontSize: 16,
    color: '#333',
  },
  itemSubtitle: {
    fontSize: 12,
    color: '#666',
    marginTop: 2,
  },
});

export default ProfilePage;