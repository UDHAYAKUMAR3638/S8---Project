import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ViewProductionLotsComponent } from '../view-production-lots/view-production-lots.component';

@Component({
  selector: 'app-production',
  templateUrl: './production.component.html',
  styleUrls: ['./production.component.scss']
})
export class ProductionComponent {
  constructor(
    private http: HttpClient,
    public dialog: MatDialog,
  ) { }
  inbounds: any;
  selectedLot: any[] =[];

  ngOnInit() {
    this.http.get("http://localhost:8080/inbound/get").subscribe({
      next: (data) => {
        this.inbounds = data;
        console.log(this.inbounds);
        
      }
    });
  }

  add(inbound: any, value: number) {
    this.selectedLot.push({ lotNo: inbound.lotNo, quantity: value });
      console.log(this.selectedLot);
  }

  openDialog(){
   this.dialog.open(ViewProductionLotsComponent,{
    data:this.selectedLot,
    width:"700px",
    height:"500px",
   })
  }

  prevImage(x: any) {
    x.currentImage--;
    console.log(x);
    
  }

  nextImage(x: any) {
    x.currentImage++;
  }

}
