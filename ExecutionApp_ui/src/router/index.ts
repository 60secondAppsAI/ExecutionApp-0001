import { createRouter, createWebHashHistory } from 'vue-router';
import { useAuth } from '../stores/auth';
import SignIn from '../components/SignIn.vue';
import Settings from '../components/Settings.vue';
import MyDashboard from '../components/MyDashboard.vue';

// Dynamic imports for module components
import Executions from '../components/Executions.vue';
import ExecutionDetail from '../components/ExecutionDetail.vue';
import ExecutionVenues from '../components/ExecutionVenues.vue';
import ExecutionVenueDetail from '../components/ExecutionVenueDetail.vue';
import ExecutionReports from '../components/ExecutionReports.vue';
import ExecutionReportDetail from '../components/ExecutionReportDetail.vue';
import ExecutionCosts from '../components/ExecutionCosts.vue';
import ExecutionCostDetail from '../components/ExecutionCostDetail.vue';

const routes = [
  {
    path: '/',
    name: 'home',
    redirect: '/dashboard'
  },
  {
    path: '/dashboard',
    name: 'Dashboard',
    component: MyDashboard
  },
  
  // Dynamic module routes
  {
    path: '/executions',
    name: 'Executions',
    component: Executions
  },
  {
    path: '/execution/:id',
    name: 'ExecutionDetail',
    component: ExecutionDetail,
    props: true
  },
  {
    path: '/executionVenues',
    name: 'ExecutionVenues',
    component: ExecutionVenues
  },
  {
    path: '/executionVenue/:id',
    name: 'ExecutionVenueDetail',
    component: ExecutionVenueDetail,
    props: true
  },
  {
    path: '/executionReports',
    name: 'ExecutionReports',
    component: ExecutionReports
  },
  {
    path: '/executionReport/:id',
    name: 'ExecutionReportDetail',
    component: ExecutionReportDetail,
    props: true
  },
  {
    path: '/executionCosts',
    name: 'ExecutionCosts',
    component: ExecutionCosts
  },
  {
    path: '/executionCost/:id',
    name: 'ExecutionCostDetail',
    component: ExecutionCostDetail,
    props: true
  },

  // Core application routes
  {
    path: '/signin',
    name: 'SignIn',
    component: SignIn
  },
//  {
//    path: '/users',
//    name: 'Users',
//    component: Users,
//    meta: { requiresAuth: true }
//  },
  {
    path: '/settings',
    name: 'Settings',
    component: Settings,
    meta: { requiresAuth: true }
  },
  {
    path: '/:pathMatch(.*)*',
    redirect: '/dashboard'
  }
];

const router = createRouter({
  history: createWebHashHistory(import.meta.env.BASE_URL),
  routes,
  scrollBehavior(to, from, savedPosition) {
    return savedPosition || { top: 0 };
  }
});

// Navigation guard for authentication
router.beforeEach((to, from, next) => {
  const auth = useAuth();
  
  if (to.meta.requiresAuth && !auth.isAuthenticated) {
    next({ name: 'SignIn', query: { redirect: to.fullPath } });
  } else if (to.name === 'SignIn' && auth.isAuthenticated) {
    next({ name: 'Dashboard' });
  } else {
    next();
  }
});

export default router;
