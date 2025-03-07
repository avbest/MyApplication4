import React, { useState } from 'react';
import { View, Text, StyleSheet, TouchableOpacity, Switch } from 'react-native';
import { useNavigation } from '@react-navigation/native';
import DateTimePicker from '@react-native-community/datetimepicker';

const CourseSettingPage = () => {
  const navigation = useNavigation();
  const [startDate, setStartDate] = useState(new Date('2025-02-17'));
  const [currentWeek, setCurrentWeek] = useState(2);
  const [totalWeeks, setTotalWeeks] = useState(20);
  const [showWeekend, setShowWeekend] = useState(true);
  const [showOtherWeeks, setShowOtherWeeks] = useState(true);
  const [showDatePicker, setShowDatePicker] = useState(false);
  const [showWeekPicker, setShowWeekPicker] = useState(false);
  const [showTotalWeeksPicker, setShowTotalWeeksPicker] = useState(false);

  const handleDateChange = (event, selectedDate) => {
    setShowDatePicker(false);
    if (selectedDate) {
      setStartDate(selectedDate);
    }
  };

  const renderHeader = () => (
    <View style={styles.header}>
      <TouchableOpacity onPress={() => navigation.goBack()} style={styles.backButton}>
        <Text style={styles.backButtonText}>←</Text>
      </TouchableOpacity>
      <Text style={styles.title}>课程表设置</Text>
    </View>
  );

  const renderBasicSettings = () => (
    <View style={styles.section}>
      <Text style={styles.sectionTitle}>基本设置</Text>
      
      <TouchableOpacity 
        style={styles.settingItem} 
        onPress={() => setShowDatePicker(true)}
      >
        <Text style={styles.settingLabel}>开始上课时间</Text>
        <Text style={styles.settingValue}>
          {startDate.toISOString().split('T')[0]}
          <Text style={styles.settingDesc}> ›</Text>
        </Text>
      </TouchableOpacity>

      <TouchableOpacity 
        style={styles.settingItem}
        onPress={() => setShowWeekPicker(true)}
      >
        <Text style={styles.settingLabel}>当前的周数</Text>
        <Text style={styles.settingValue}>
          {currentWeek}
          <Text style={styles.settingDesc}> ›</Text>
        </Text>
      </TouchableOpacity>

      <TouchableOpacity 
        style={styles.settingItem}
        onPress={() => setShowTotalWeeksPicker(true)}
      >
        <Text style={styles.settingLabel}>本学期总周数</Text>
        <Text style={styles.settingValue}>
          {totalWeeks}
          <Text style={styles.settingDesc}> ›</Text>
        </Text>
      </TouchableOpacity>
    </View>
  );

  const renderDisplaySettings = () => (
    <View style={styles.section}>
      <View style={styles.settingItem}>
        <Text style={styles.settingLabel}>是否显示周末</Text>
        <Switch
          value={showWeekend}
          onValueChange={setShowWeekend}
          trackColor={{ false: '#767577', true: '#4285f4' }}
        />
      </View>

      <View style={styles.settingItem}>
        <Text style={styles.settingLabel}>是否显示非本周课程</Text>
        <Switch
          value={showOtherWeeks}
          onValueChange={setShowOtherWeeks}
          trackColor={{ false: '#767577', true: '#4285f4' }}
        />
      </View>
    </View>
  );

  return (
    <View style={styles.container}>
      {renderHeader()}
      {renderBasicSettings()}
      {renderDisplaySettings()}

      {showDatePicker && (
        <DateTimePicker
          value={startDate}
          mode="date"
          display="default"
          onChange={handleDateChange}
        />
      )}
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#f5f5f5',
  },
  header: {
    flexDirection: 'row',
    alignItems: 'center',
    padding: 16,
    backgroundColor: '#fff',
    borderBottomWidth: 1,
    borderBottomColor: '#e0e0e0',
  },
  backButton: {
    padding: 8,
  },
  backButtonText: {
    fontSize: 24,
    color: '#000',
  },
  title: {
    fontSize: 18,
    fontWeight: 'bold',
    marginLeft: 16,
  },
  section: {
    marginTop: 16,
    backgroundColor: '#fff',
    paddingHorizontal: 16,
  },
  sectionTitle: {
    fontSize: 14,
    color: '#666',
    marginVertical: 8,
  },
  settingItem: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    paddingVertical: 16,
    borderBottomWidth: 1,
    borderBottomColor: '#e0e0e0',
  },
  settingLabel: {
    fontSize: 16,
    color: '#333',
  },
  settingValue: {
    fontSize: 16,
    color: '#666',
  },
  settingDesc: {
    color: '#999',
  },
});

export default CourseSettingPage;