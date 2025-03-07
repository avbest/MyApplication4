import React from 'react';
import { View, Text, StyleSheet, ScrollView, TouchableOpacity } from 'react-native';
import Icon from 'react-native-vector-icons/MaterialIcons';

const CourseCard = ({ title, time, location }) => (
  <View style={styles.card}>
    <View style={styles.cardHeader}>
      <Icon name="book" size={24} color="#1976D2" />
      <Text style={styles.cardTitle}>{title}</Text>
    </View>
    <View style={styles.cardContent}>
      <View style={styles.infoRow}>
        <Icon name="access-time" size={20} color="#666" />
        <Text style={styles.infoText}>{time}</Text>
      </View>
      <View style={styles.infoRow}>
        <Icon name="location-on" size={20} color="#666" />
        <Text style={styles.infoText}>{location}</Text>
      </View>
    </View>
  </View>
);

const CoursePage = () => {
  const courses = [
    { id: 1, title: '高等数学', time: '周一 8:00-9:40', location: '教学楼A-101' },
    { id: 2, title: '大学物理', time: '周二 10:00-11:40', location: '教学楼B-202' },
    { id: 3, title: '程序设计', time: '周三 14:00-15:40', location: '实验楼C-303' },
  ];

  return (
    <View style={styles.container}>
      <View style={styles.header}>
        <Text style={styles.headerTitle}>我的课程</Text>
        <TouchableOpacity style={styles.addButton}>
          <Icon name="add" size={24} color="#1976D2" />
        </TouchableOpacity>
      </View>
      <ScrollView style={styles.courseList}>
        {courses.map(course => (
          <CourseCard
            key={course.id}
            title={course.title}
            time={course.time}
            location={course.location}
          />
        ))}
      </ScrollView>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#F5F5F5',
  },
  header: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    padding: 16,
    backgroundColor: '#FFF',
    elevation: 2,
  },
  headerTitle: {
    fontSize: 20,
    fontWeight: 'bold',
    color: '#333',
  },
  addButton: {
    padding: 8,
  },
  courseList: {
    flex: 1,
    padding: 16,
  },
  card: {
    backgroundColor: '#FFF',
    borderRadius: 8,
    padding: 16,
    marginBottom: 16,
    elevation: 1,
  },
  cardHeader: {
    flexDirection: 'row',
    alignItems: 'center',
    marginBottom: 12,
  },
  cardTitle: {
    fontSize: 18,
    fontWeight: 'bold',
    marginLeft: 8,
    color: '#333',
  },
  cardContent: {
    marginLeft: 32,
  },
  infoRow: {
    flexDirection: 'row',
    alignItems: 'center',
    marginBottom: 8,
  },
  infoText: {
    marginLeft: 8,
    color: '#666',
    fontSize: 14,
  },
});

export default CoursePage;