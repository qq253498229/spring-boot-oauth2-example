import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowPersonalDetailComponent } from './show-personal-detail.component';

describe('ShowPersonalDetailComponent', () => {
  let component: ShowPersonalDetailComponent;
  let fixture: ComponentFixture<ShowPersonalDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShowPersonalDetailComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowPersonalDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
