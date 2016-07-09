import { Component } from '@angular/core';
import { AppService } from './service.app';

@Component({
  selector: 'my-app',
  template: `<button (click)="getSomething()">get</button>
    Some: {{result}}`,
  providers: [AppService]
})

export class AppComponent { 
    constructor(private appService: AppService){}
   
    result: string;
    getSomething(){
        this.appService.getSome()
            .subscribe(
                data => this.result = JSON.stringify(data.string[0]),
                error => alert(error)
            );
    }
     
    
}