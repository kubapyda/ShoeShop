import { MainController } from './main/main.controller';
import { NavbarDirective } from '../app/components/navbar/navbar.directive';
import { config } from './index.config';
import { routerConfig } from './index.route';
import { runBlock } from './index.run';

angular.module('ShoeShop', ['ngResource', 'ngRoute', 'ui.bootstrap', 'toastr'])
  .config(config)
  .config(routerConfig)
  .run(runBlock)
  .controller('MainController', MainController)
  .directive('acmeNavbar', NavbarDirective)
