import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import { View, Text, StyleSheet } from 'react-native';
import Icon from 'react-native-vector-icons/MaterialIcons';

import CoursePage from './screens/CoursePage';
import StatusPage from './screens/StatusPage';
import ProfilePage from './screens/ProfilePage';

const Tab = createBottomTabNavigator();

const App = () => {
  return (
    <NavigationContainer>
      <Tab.Navigator
        screenOptions={({ route }) => ({
          tabBarIcon: ({ focused, color, size }) => {
            let iconName;

            if (route.name === '课程') {
              iconName = 'event-note';
            } else if (route.name === '状态') {
              iconName = 'info';
            } else if (route.name === '我的') {
              iconName = 'person';
            }

            return <Icon name={iconName} size={size} color={color} />;
          },
          tabBarActiveTintColor: '#1976D2',
          tabBarInactiveTintColor: 'gray',
        })}
      >
        <Tab.Screen name="课程" component={CoursePage} />
        <Tab.Screen name="状态" component={StatusPage} />
        <Tab.Screen name="我的" component={ProfilePage} />
      </Tab.Navigator>
    </NavigationContainer>
  );
};

const styles = StyleSheet.create({
  page: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
});

export default App;