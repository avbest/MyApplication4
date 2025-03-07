import React from 'react';
import { View, Text, StyleSheet, ScrollView, TouchableOpacity } from 'react-native';
import Icon from 'react-native-vector-icons/MaterialIcons';

const StatusCard = ({ title, status, time }) => (
  <View style={styles.card}>
    <View style={styles.cardHeader}>
      <Icon name="info" size={24} color="#1976D2" />
      <Text style={styles.cardTitle}>{title}</Text>
    </View>
    <View style={styles.cardContent}>
      <View style={styles.infoRow}>
        <Icon name="circle" size={20} color={status === '正常' ? '#4CAF50' : '#F44336'} />
        <Text style={[styles.infoText, { color: status === '正常' ? '#4CAF50' : '#F44336' }]}>{status}</Text>
      </View>
      <View style={styles.infoRow}>
        <Icon name="access-time" size={20} color="#666" />
        <Text style={styles.infoText}>{time}</Text>
      </View>
    </View>
  </View>
);

const StatusPage = () => {
  const statuses = [
    { id: 1, title: '校园网状态', status: '正常', time: '更新于 10:00' },
    { id: 2, title: '图书馆开放状态', status: '正常', time: '更新于 09:30' },
    { id: 3, title: '食堂就餐状态', status: '拥挤', time: '更新于 11:30' },
  ];

  return (
    <View style={styles.container}>
      <View style={styles.header}>
        <Text style={styles.headerTitle}>校园状态</Text>
        <TouchableOpacity style={styles.refreshButton}>
          <Icon name="refresh" size={24} color="#1976D2" />
        </TouchableOpacity>
      </View>
      <ScrollView style={styles.statusList}>
        {statuses.map(status => (
          <StatusCard
            key={status.id}
            title={status.title}
            status={status.status}
            time={status.time}
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
  refreshButton: {
    padding: 8,
  },
  statusList: {
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
    fontSize: 14,
  },
});

export default StatusPage;